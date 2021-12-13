package com.nisum.users.api.service;

import com.nisum.users.api.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(Integer id);

    List<User> findAll();

    void save(User user);
}
