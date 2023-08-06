package com.example.demo.services;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUserByUsername(String username) {
        if (username.equals("admin"))
            return User.builder()
                    .id(1L)
                    .username(username)
                    .password("admin")
                    .build();
        else
            return null;
    }

    public boolean passwordMatches(String userPassword, String dbPassword) {
        if (userPassword.equals(dbPassword))
            return true;
        return false;
    }
}
