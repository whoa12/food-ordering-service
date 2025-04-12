package com.project.MyProject.Task.User.Service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MyProject.Task.User.Service.config.Jwtprovider;
import com.project.MyProject.Task.User.Service.model.User;
import com.project.MyProject.Task.User.Service.service.repository.UserRepo;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepo repo;

	@Override
	public User getUserProfile(String jwt) {
		String email = Jwtprovider.getEmailFromJwtToken(jwt);
		return repo.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	

}
