package com.project.MyProject.Task.User.Service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MyProject.Task.User.Service.model.User;
import com.project.MyProject.Task.User.Service.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt){
		if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);  
        }
		User user = service.getUserProfile(jwt);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = service.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
      
}
