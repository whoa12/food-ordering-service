package com.project.MyProject.Task.User.Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.MyProject.Task.User.Service.model.User;
import com.project.MyProject.Task.User.Service.service.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repo.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("user not found!");
		}
		return new com.project.MyProject.Task.User.Service.model.UserPrincipal(user);
		
	}
	

}
