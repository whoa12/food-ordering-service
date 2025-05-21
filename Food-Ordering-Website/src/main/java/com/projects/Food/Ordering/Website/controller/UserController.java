package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception{

        User user  = userService.findUserByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
