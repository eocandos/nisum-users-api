package com.nisum.users.api.service.impl;

import com.nisum.users.api.constants.Messages;
import com.nisum.users.api.entity.Message;
import com.nisum.users.api.entity.User;
import com.nisum.users.api.exception.CustomException;
import com.nisum.users.api.repository.UserRepository;
import com.nisum.users.api.service.UserService;
import com.nisum.users.api.utils.JWTToken;
import com.nisum.users.api.utils.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findOne(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity save(User user) {
        try {
            UserValidations.userValidation(user);
            Optional<User> u = findByEmail(user.getEmail());
            if(u.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(Messages.EMAIL_EXIST));
            }
            String token = JWTToken.getJWTToken(user.getEmail());
            user.setToken(token);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(e.getMessage()));
        }
    }

}