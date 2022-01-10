package com.nisum.users.api.service;

import com.nisum.users.api.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /** Find User by Id
     * @param id
     * @return User
     */
    Optional<User> findOne(Integer id);

    /** Find User by Email
     * @param email
     * @return User
     */
    Optional<User> findByEmail(String email);

    /** Find all Users
     * @return
     */
    List<User> findAll();

    /** Save new user
     * @param user
     */
    ResponseEntity save(User user);

}
