package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
