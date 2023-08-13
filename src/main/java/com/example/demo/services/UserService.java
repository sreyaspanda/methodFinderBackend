package com.example.demo.services;

import com.example.demo.entity.User;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.demo.utilities.HttpUtility.addEntryToJson;

@Service
public class UserService {

    public User findUserByUsername(String username) throws IOException, ParseException {
        addEntryToJson("UserService", "findUserByUsername");
        if (username.equals("admin"))
            return User.builder()
                    .id(1L)
                    .username(username)
                    .password("admin")
                    .build();
        else
            return null;
    }

    public boolean passwordMatches(String userPassword, String dbPassword) throws IOException, ParseException {
        addEntryToJson("UserService", "passwordMatches");
        if (userPassword.equals(dbPassword))
            return true;
        return false;
    }
}
