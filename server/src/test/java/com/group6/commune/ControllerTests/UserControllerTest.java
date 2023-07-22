package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.UserController;
import com.group6.commune.Model.User;
import com.group6.commune.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userService.createUser(any(User.class))).thenReturn(true);

        boolean result = userController.createAccount(user);

        assertTrue(result);
    }

    @Test
    public void testGetUserDetailsById() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userService.getUserDetailsById(1)).thenReturn(user);

        User result = userController.getUserDetailsById(1);

        assertTrue(result.getUserId() == 1);
        assertTrue(result.getFirstName().equals("John"));
        assertTrue(result.getLastName().equals("Doe"));
        assertTrue(result.getEmail().equals("john.doe@example.com"));
    }

    @Test
    public void testUpdateAccount() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userService.updateAccountDetails(any(User.class))).thenReturn(true);

        boolean result = userController.updateCommunity(user);

        assertTrue(result);
    }

    @Test
    public void testDeleteUserByUserId() {
        when(userService.deleteUserAccountById(1)).thenReturn(true);

        boolean result = userController.deleteUserByUserId(1);

        assertTrue(result);
    }

}
