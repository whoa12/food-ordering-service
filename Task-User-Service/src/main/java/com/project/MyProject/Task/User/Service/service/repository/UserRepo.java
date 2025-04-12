package com.project.MyProject.Task.User.Service.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.MyProject.Task.User.Service.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public User findByEmail(String email);

}
