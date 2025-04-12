package com.MyProject.SubmissionService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.MyProject.SubmissionService.model.Submission;
import com.MyProject.SubmissionService.model.TaskDto;

@EnableMongoRepositories
public interface SubmissionRepo extends MongoRepository<Submission, Long> {

	Submission save(TaskDto currentTask);

	Optional<Submission> findById(Long submissionId);

	Optional<Submission> findBySubmissionId(Long submissionId);

	List<Submission> findByTaskId(Long taskId);

}
