package com.nisum.users.api.controller;

import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Get all users
     * @return List<User>
     */
    @GetMapping("user")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    /**
     * Create one User
     * @param user
     * @return ResponseEntity
     */
    @PostMapping("user")
    public ResponseEntity create(@RequestBody User user) {
        return userService.save(user);
    }

}