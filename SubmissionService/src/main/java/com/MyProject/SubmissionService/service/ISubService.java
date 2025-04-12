package com.MyProject.SubmissionService.service;

import java.util.List;
import com.MyProject.SubmissionService.model.Submission;

public interface ISubService {
	Submission submitTask(Long UserId, Long taskId, String githubLink, String jwt) throws Exception;
	Submission getSubmittedTaskById(Long submissionId) throws Exception;
	List<Submission> getAllSubmissions();
	Submission acceptOrDeclineSub(Long id, String status) throws Exception;
	List<Submission> getSubmittedTaskByTaskId(Long taskId);

}
