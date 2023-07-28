package com.group6.commune.Controller;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.UnauthorizedAccessException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.LoginResponseDTO;
import com.group6.commune.Model.User;
import com.group6.commune.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/Signup")
    public ResponseEntity<String> createAccount(@RequestBody User user){
        return userService.createUser(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetailsById(@PathVariable int id){
        return userService.getUserDetailsById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<String> updateUserDetails(@RequestBody User user){
        return userService.updateAccountDetails(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable int id){
        return userService.deleteUserAccountById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/forgotPassword")
    public int createVerificationCode(@RequestParam String email){
        return userService.createVerificationCode(email);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/resetPassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user)
    {
        return userService.updateUserPassword(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public LoginResponseDTO generateToken(@RequestBody User body){
        return userService.loginUser(body.getEmail(), body.getPassword());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage(), "errors", ex.getErrors()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", ex.getMessage()));
    }
}
