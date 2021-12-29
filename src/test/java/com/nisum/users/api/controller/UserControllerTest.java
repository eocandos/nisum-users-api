package com.nisum.users.api.controller;

import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import org.junit.Assert;
import org.junit.Before;
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
import java.util.Optional;

import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final Integer USER_ID = 1;
    private static final String USER_EMAIL = "test@mail.com";
    private static final String USER_PASSWORD = "Test123*";

    User user = new User();
    List<User> userList = new ArrayList<>();

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        user.setEmail(USER_EMAIL);
        user.setId(USER_ID);
        user.setPassword(USER_PASSWORD);
        List<User> userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    public void get_all_users_should_return_OK_200() {
        when(userService.findAll()).thenReturn(userList);
        ResponseEntity<User> responseEntity = userController.getUsers();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void create_user_should_return_OK_200() {
        ResponseEntity<User> responseEntity = userController.create(user);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void create_user_EMAIL_EXIST_should_return_BAD_REQUEST() {

        Optional u = Optional.of(user);

        // Test
        when(userService.findByEmail(USER_EMAIL)).thenReturn(u);
        ResponseEntity<User> responseEntity = userController.create(user);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void create_user_format_validation_should_return_BAD_REQUEST() {

        Optional u = Optional.of(user);

        // Test
        when(userService.findByEmail(USER_EMAIL)).thenReturn(u);
        ResponseEntity<User> responseEntity = userController.create(user);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test(expected = Exception.class)
    public void create_user_should_return_Exception_BAD_REQUEST() throws Exception {
        when(userService.findByEmail(null)).thenThrow(Exception.class);
        ResponseEntity<User> responseEntity = userController.create(null);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
