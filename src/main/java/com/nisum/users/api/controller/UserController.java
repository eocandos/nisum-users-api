package com.nisum.users.api.controller;

import com.nisum.users.api.constants.Messages;
import com.nisum.users.api.entity.Message;
import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import com.nisum.users.api.utils.JWTToken;
import com.nisum.users.api.utils.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

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

        Message message = UserValidations.userData(user);
        if (message.getMensaje().equals("")) {
            try {
                Optional<User> u = userService.findByEmail(user.getEmail());
                if(!u.isPresent()){
                    String token = JWTToken.getJWTToken(user.getEmail());
                    user.setToken(token);
                    userService.save(user);
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new Message(Messages.EMAIL_EXIST));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Message(Messages.USER_CREATION_NOT_POSSIBLE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

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