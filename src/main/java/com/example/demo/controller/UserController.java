package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.demo.utilities.HttpUtility.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String userName,
                                   @RequestParam("password") String password) throws IOException, ParseException {
        addEntryToJson("UserController", "login");
        User user = userService.findUserByUsername(userName);

        // Check if the user exists and the password matches the one in the database
        if (user != null && userService.passwordMatches(password, "admin")) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/startRun")
    public ResponseEntity<?> startRunFlag() {
        checkNewRunFlag(true);
        return ResponseEntity.ok("Run started");
    }

    @GetMapping("/methodsList")
    public ResponseEntity<?> getMethodsList() throws ParseException, JsonProcessingException {
        JSONObject jsonObject = readFile();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return ResponseEntity.ok(json);
    }
}

