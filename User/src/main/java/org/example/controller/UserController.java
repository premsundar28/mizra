package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam("id") String id){
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) throws JsonProcessingException {
        userService.addUser(user);
    }
}