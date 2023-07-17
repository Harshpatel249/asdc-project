package com.group6.commune.Controller;

import com.group6.commune.Model.User;
import com.group6.commune.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetailsById(@PathVariable int id){
        return userService.getUserDetailsById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateUserDetails(@RequestBody User user){
        return userService.updateAccountDetails(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable int id){
        return userService.deleteUserAccountById(id);
    }

    @PostMapping("/forgotPassword")
    public int createVerificationCode(@RequestParam String email){
        return userService.createVerificationCode(email);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody User user)
    {
        return userService.updateUserPassword(user);
    }

}
