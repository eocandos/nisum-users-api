package com.nisum.users.api.service.impl;

import com.nisum.users.api.entity.User;
import com.nisum.users.api.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceImpl.class)
public class UserServiceImplTest {

    private static final Integer USER_ID = 1;
    private static final String USER_EMAIL = "test@mail.com";
    private static final String USER_PASSWORD = "Test123*";

    User user = new User();
    List<User> userList = new ArrayList<>();

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        user.setEmail(USER_EMAIL);
        user.setId(USER_ID);
        user.setPassword(USER_PASSWORD);
        List<User> userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    public void get_one_user_by_id_should_return_OK_200() {
        Optional<User> u = Optional.of(user);
        when(userRepository.findById(USER_ID)).thenReturn(u);
        Assert.assertEquals(userService.findOne(USER_ID),u);
    }

    @Test
    public void get_user_by_email_should_return_OK_200() {
        Optional<User> u = Optional.of(user);
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(u);
        Assert.assertEquals(userService.findByEmail(USER_EMAIL),u);
    }

    @Test
    public void get_all_users_should_return_OK_200() {
        when(userRepository.findAll()).thenReturn(userList);
        Assert.assertEquals(userService.findAll(),userList);
    }

    @Test
    public void create_a_new_user_should_return_OK_200() {
        userService.save(user);
    }


}
