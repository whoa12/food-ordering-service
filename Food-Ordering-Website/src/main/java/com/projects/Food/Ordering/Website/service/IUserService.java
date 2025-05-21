package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.User;

public interface IUserService {

    public User findUserByJwt(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
