package com.project.MyProject.Task.User.Service.service;

import java.util.List;

import com.project.MyProject.Task.User.Service.model.User;

public interface IUserService {
	public User getUserProfile(String jwt);
	
	public List<User> getAllUsers();

}
