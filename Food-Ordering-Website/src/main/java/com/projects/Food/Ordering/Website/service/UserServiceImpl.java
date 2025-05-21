package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.repository.UserRepository;
import com.projects.Food.Ordering.Website.securityconfiguration.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    private final JwtGenerator jwtGenerator;

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtGenerator.getEmailFromJwt(jwt);
        if(jwt!=null && jwt.startsWith("Bearer ")){
            jwt = jwt.substring(7);
        }
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found!");
        }
        return user;
    }
}
