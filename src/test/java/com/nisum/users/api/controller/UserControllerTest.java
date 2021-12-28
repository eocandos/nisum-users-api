package com.nisum.users.api.controller;

import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)//we test only the IndexController
public class UserControllerTest {

    private static final Integer USER_ID = 1;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void get_all_users_should_return_a_list_from_users() {

        // Objects
        User user = new User();
        user.setId(USER_ID);
        List<User> userList = new ArrayList<>();
        userList.add(user);

        // Test
        when(userService.findAll()).thenReturn(userList);
        ResponseEntity<User> responseEntity = userController.getUsers();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

//    @Test
//    public void get_all_users_should_return_a_list_from_users() {
//
//        User user = new User();
//        user.setId(USER_ID);
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//
//        when(userService.findAll()).thenReturn(userList);
//        List<User> users = userService.findAll();
//        Assertions.assertEquals(1, users.size());
//
//    }

}
