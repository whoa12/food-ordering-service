package com.projects.Food.Ordering.Website.service;


import com.projects.Food.Ordering.Website.model.USER_ROLE;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Not found!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        USER_ROLE role = user.getRole();
        if(role==null){
            role = USER_ROLE.ROLE_CUSTOMER;
        }
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
