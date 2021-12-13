package com.nisum.users.api.controller;

import com.nisum.users.api.constants.Messages;
import com.nisum.users.api.entity.Message;
import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import com.nisum.users.api.utils.EmailValidator;
import com.nisum.users.api.utils.JWTToken;
import com.nisum.users.api.utils.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
// @RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Get all users
     * @return List<User>
     */
    @GetMapping("user")
    public List<User> getUsers() {
        return userService.findAll();
    }


    /**
     * Create one User
     * @param user
     * @return ResponseEntity
     */
    @PostMapping("user")
    public ResponseEntity create(@RequestBody User user) {

        if(!EmailValidator.isValid(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.INVALID_EMAIL));
        }
        if(!PasswordValidator.isValid(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.PLEASE_VALIDATE_PASSWORD_FORMAT));
        }

        try {
            Optional<User> u = userService.findByEmail(user.getEmail());
            if(!u.isPresent()){
                String token = JWTToken.getJWTToken(user.getEmail());
                user.setToken(token);
                userService.save(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.EMAIL_EXIST));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.USER_CREATION_NOT_POSSIBLE));
        }
    }

    /**
     * Edit one User
     * @param user
     * @return ResponseEntity
     */
    @PutMapping("user")
    public ResponseEntity edit(@RequestBody User user) {
        try {
            Optional u = userService.findOne(user.getId());
            if(u.isPresent()) {
                userService.save(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.ID_DONT_EXIST));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.USER_EDITION_NOT_POSSIBLE));
        }
    }


}
