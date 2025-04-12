package com.MyProject.SubmissionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyProject.SubmissionService.model.Submission;
import com.MyProject.SubmissionService.model.UserDto;
import com.MyProject.SubmissionService.service.SubService;
import com.MyProject.SubmissionService.service.TaskService;
import com.MyProject.SubmissionService.service.UserService;

@RequestMapping("/api/submissions")
public class SubmissionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SubService submissionService;
	
	@PostMapping()
	public ResponseEntity<Submission> submitTask(
			@RequestParam Long task_id,
			@RequestParam String github_Link,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.submitTask(user.getId(), task_id, github_Link, jwt);
				return new ResponseEntity<Submission>(submission, HttpStatus.CREATED);
		
		
	}
	@GetMapping("/{submissionId}")
	public ResponseEntity<Submission> findSubmittedTaskById(
		    @PathVariable Long submissionId,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.getSubmittedTaskById(submissionId);
				return new ResponseEntity<Submission>(submission, HttpStatus.OK);
		
		
	}
	@GetMapping()
	public ResponseEntity<List<Submission>> findAllSubmissions(
		    @PathVariable Long submissionId,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		List<Submission> submission = submissionService.getAllSubmissions();
				return new ResponseEntity<List<Submission>>(submission, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<List<Submission>> findSubmittedTaskByTaskId(
		    @PathVariable Long taskId,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		List<Submission> submission = submissionService.getSubmittedTaskByTaskId(taskId);
				return new ResponseEntity<List<Submission>>(submission, HttpStatus.OK);}

	
	
	@PutMapping("/submissionId")
	public ResponseEntity<Submission> acceptOrDecline(@PathVariable Long submissionId,
			@RequestParam("status") String status,
			@RequestHeader("Authorization") String jwt) throws Exception{
		Submission submission =submissionService.acceptOrDeclineSub(submissionId, status);
		return new ResponseEntity<>(submission, HttpStatus.CREATED);
	}
	
}
