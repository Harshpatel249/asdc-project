package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.UserController;
import com.group6.commune.Model.User;
import com.group6.commune.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        ResponseEntity<String> response=new ResponseEntity<>("User account got created successfully", HttpStatus.CREATED);
        when(userService.createUser(any(User.class))).thenReturn(response);

        ResponseEntity<String> result = userController.createAccount(user);

        assertEquals(HttpStatus.CREATED,result.getStatusCode());
    }

    @Test
    public void testGetUserDetailsById() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());
        ResponseEntity<User> response=new ResponseEntity<>(user, HttpStatus.OK);
        when(userService.getUserDetailsById(1)).thenReturn(response);

        ResponseEntity<User> result = userController.getUserDetailsById(1);

        assertTrue(result.getBody().getUserId() == 1);
        assertTrue(result.getBody().getFirstName().equals("John"));
        assertTrue(result.getBody().getLastName().equals("Doe"));
        assertTrue(result.getBody().getEmail().equals("john.doe@example.com"));
    }

    @Test
    public void testUpdateAccount() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());
        ResponseEntity<String> response=new ResponseEntity<>("User account details got updated successfully", HttpStatus.ACCEPTED);
        when(userService.updateAccountDetails(any(User.class))).thenReturn(response);

        ResponseEntity<String> result = userController.updateUserDetails(user);

        assertEquals(HttpStatus.ACCEPTED,result.getStatusCode());
    }

    @Test
    public void testDeleteUserByUserId() {
        ResponseEntity<String> response=new ResponseEntity<>("User deleted successfully", HttpStatus.OK);

        when(userService.deleteUserAccountById(1)).thenReturn(response);

        ResponseEntity<String> result= userController.deleteUserByUserId(1);

        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

}
