package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.USER_ROLE;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.repository.CartRepository;
import com.projects.Food.Ordering.Website.repository.UserRepository;
import com.projects.Food.Ordering.Website.request.LoginRequest;
import com.projects.Food.Ordering.Website.response.AuthResponse;
import com.projects.Food.Ordering.Website.securityconfiguration.JwtGenerator;
import com.projects.Food.Ordering.Website.service.CustomerUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    private final CustomerUserDetailsService customerUserDetailsService;
    private final CartRepository cartRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail!=null){
            throw new Exception("User already exists with the given E-mail!");

        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(createdUser);

        Cart cart  = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtGenerator.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful!");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        String username = request.getEmail();
        String password = request.getPassword();
        Authentication authentication = authenticate(username, password);
        String jwt = jwtGenerator.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successful!");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(role));
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {

            throw new BadCredentialsException("Invalid username!");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }


}
