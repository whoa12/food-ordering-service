package com.MyProject.SubmissionService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.MyProject.SubmissionService.model.TaskDto;

@FeignClient(name="SUBMISSION-SERVICE" , url="http://localhost:8081")
public interface TaskService {
	@GetMapping("/api/tasks/{id}")
	public TaskDto findTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception;
	@PutMapping("/api/tasks/{id}/complete")
	public TaskDto completedTask(@PathVariable Long id ) throws Exception;
    
}
