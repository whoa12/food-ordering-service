package com.MyProject.SubmissionService.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyProject.SubmissionService.model.Submission;
import com.MyProject.SubmissionService.model.TaskDto;
import com.MyProject.SubmissionService.repository.SubmissionRepo;

@Service
public class SubService implements ISubService {
	@Autowired
	private SubmissionRepo submissionRepo;
	
	@Autowired
	private TaskService taskService;

	@Override
	public Submission submitTask(Long userId, Long taskId, String githubLink, String jwt) throws Exception {
		TaskDto currentTask = taskService.findTaskById(taskId, jwt);
       		if(currentTask!=null) {
       			Submission submission = new Submission();
       			submission.setTaskId(taskId);
       			submission.setUserId(userId);
       			submission.setGithubLink(githubLink);
       			submission.setSubmissionTime(LocalDateTime.now());
       			return submissionRepo.save(submission);
       			
       			
       		}
       		throw new Exception("Task not find with id: "+ taskId);
	}

	@Override
	public Submission getSubmittedTaskById(Long submissionId) throws Exception {
        return submissionRepo.findBySubmissionId(submissionId)
                .orElseThrow(() -> new Exception("Submission not found with ID: " + submissionId));
    }


	@Override
	public List<Submission> getAllSubmissions() {
		return submissionRepo.findAll();
	}

	@Override
	public Submission acceptOrDeclineSub(Long id, String status) throws Exception {
		Submission submission = getSubmittedTaskById(id);
		submission.setStatus(status);
		if(status.equals("ACCEPTED")) {
		taskService.completedTask(submission.getSubmissionId());}

		return submissionRepo.save(submission);
	}

	@Override
	public List<Submission> getSubmittedTaskByTaskId(Long taskId) {
		// TODO Auto-generated method stub
		return submissionRepo.findByTaskId(taskId);
	}
   
}
