package com.ey.challenge.controller;

import com.ey.challenge.dto.UserDTO;
import com.ey.challenge.entity.User;
import com.ey.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String get() {
        return "test";
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok().body(createdUser);
        //return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }
}