package com.group6.commune.ServiceTests;
import com.group6.commune.Model.EmailDetails;
import com.group6.commune.Model.User;
import com.group6.commune.Repository.EmailTemplateRepositoryImpl;
import com.group6.commune.Repository.UserRepository;
import com.group6.commune.Service.UserServiceImpl;
import com.group6.commune.Utils.CommuneEmailAgent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailTemplateRepositoryImpl emailTemplateRepo;

    @Mock
    private CommuneEmailAgent mailAgent;

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

        ResponseEntity<User> response=new ResponseEntity<>(user, HttpStatus.OK);

        when(userRepository.getUserDetailsByID(1)).thenReturn(response);

        ResponseEntity<User> result = userService.getUserDetailsById(1);

        assertEquals(result.getBody().getUserId(), 1);
        assertEquals(result.getBody().getFirstName(),"John");
        assertEquals(result.getBody().getLastName(),"Doe");
        assertEquals(result.getBody().getEmail(),"john.doe@example.com");
    }

    @Test
    public void testCreateUser() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        ResponseEntity<String> response=new ResponseEntity<>("User account got created successfully", HttpStatus.CREATED);
        when(userRepository.createUserAccount(user)).thenReturn(response);

        EmailDetails emailDetails = new EmailDetails("","","","");
        when(emailTemplateRepo.getEmailDetailsFromDB(1)).thenReturn(emailDetails);

        when(mailAgent.sendSimpleMail(emailDetails)).thenReturn("Mail Sent Successfully...");

        ResponseEntity<String> result = userService.createUser(user);

        assertEquals(HttpStatus.CREATED,result.getStatusCode());

    }

    @Test
    public void testUpdateAccountDetails() {
        User user = new User(1, "John", "Doe", new Date(), "Male", "john.doe@example.com",
                "1234567890", "password123", "profilePic.jpg", new Date());

        ResponseEntity<String> response=new ResponseEntity<>("User account details got updated successfully", HttpStatus.ACCEPTED);
        when(userRepository.updateAccountDetails(any(User.class))).thenReturn(response);

        ResponseEntity<String> result = userService.updateAccountDetails(user);

        assertEquals(HttpStatus.ACCEPTED,result.getStatusCode());
    }

    @Test
    public void testDeleteUserAccountById() {
        ResponseEntity<String> response=new ResponseEntity<>("User deleted successfully", HttpStatus.OK);

        when(userRepository.deleteUserAccountById(1)).thenReturn(response);

        ResponseEntity<String> result = userService.deleteUserAccountById(1);

        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

}
