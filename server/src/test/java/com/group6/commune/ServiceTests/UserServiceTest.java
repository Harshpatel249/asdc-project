package com.group6.commune.ServiceTests;
import com.group6.commune.Model.User;
import com.group6.commune.Repository.UserRepository;
import com.group6.commune.Service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserDetailsById() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userRepository.getUserDetailsByID(1)).thenReturn(user);

        User result = userService.getUserDetailsById(1);

        assertTrue(result.getUserId() == 1);
        assertTrue(result.getFirstName().equals("John"));
        assertTrue(result.getLastName().equals("Doe"));
        assertTrue(result.getEmail().equals("john.doe@example.com"));
    }

    @Test
    public void testCreateUser() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userRepository.createUserAccount(any(User.class))).thenReturn(true);

        boolean result = userService.createUser(user);

        assertTrue(result);
    }

    @Test
    public void testUpdateAccountDetails() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        when(userRepository.updateAccountDetails(any(User.class))).thenReturn(true);

        boolean result = userService.updateAccountDetails(user);

        assertTrue(result);
    }

    @Test
    public void testDeleteUserAccountById() {
        when(userRepository.deleteUserAccountById(1)).thenReturn(true);

        boolean result = userService.deleteUserAccountById(1);

        assertTrue(result);
    }

}
