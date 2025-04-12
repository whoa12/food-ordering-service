package com.project.MyProject.Task.User.Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MyProject.Task.User.Service.ApiResponse.AuthResponse;
import com.project.MyProject.Task.User.Service.config.Jwtprovider;
import com.project.MyProject.Task.User.Service.model.User;
import com.project.MyProject.Task.User.Service.request.LoginRequest;
import com.project.MyProject.Task.User.Service.service.MyUserDetailsService;
import com.project.MyProject.Task.User.Service.service.repository.UserRepo;
@RestController
@RequestMapping("/auth")

public class AuthController {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private Jwtprovider jwtProvider;
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		String role = user.getRole();
		
		User userEmail = repo.findByEmail(email);
		
		if(userEmail!=null) {
			throw new Exception("Email already present");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setFullName(fullName);
		createdUser.setRole(role);
		createdUser.setPassword(passwordEncoder.encode(password));
		
		User savedUser = repo.save(createdUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
			   email, password
			);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();

		authResponse.setJwt(token);
		authResponse.setMessage("Registration successful!");
		authResponse.setStatus(true);
		
		return new ResponseEntity<>(authResponse, org.springframework.http.HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> userSignin(@RequestBody LoginRequest loginRequest){
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		System.out.println(username + "-----" + password);
		
	    Authentication auhentication = authenticate(username, password);
	    SecurityContextHolder.getContext().setAuthentication(auhentication);
	    String token = Jwtprovider.generateToken(auhentication);
	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setJwt(token);
	    authResponse.setMessage("Login successful");
	    authResponse.setStatus(true);
	    
	    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		
		
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		System.out.println("sign in userDetails: " + userDetails);
		if(userDetails == null) {
			System.out.println("Sign in credentials - null " + userDetails);
			throw new BadCredentialsException("Invalid credentials");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("Password does not match");
			throw new BadCredentialsException("Invalid credentials");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	

}
