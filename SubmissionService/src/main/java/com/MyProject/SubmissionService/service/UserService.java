package com.MyProject.SubmissionService.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.MyProject.SubmissionService.model.UserDto;


public interface UserService {
	@GetMapping("/profile")
	UserDto getUserProfile(@RequestHeader("Authorization") String jwt);

}
