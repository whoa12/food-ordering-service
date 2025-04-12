package com.MyProject.SubmissionService.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
	private Long submissionId;
	private long userId;
	private long taskId;
	private String githubLink;
	private LocalDateTime submissionTime;
	private String status;

}
