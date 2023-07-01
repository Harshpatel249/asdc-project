package com.group6.commune.Controller;

import com.group6.commune.Model.User;
import com.group6.commune.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public Boolean createAccount(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserDetailsById(@PathVariable int id){
        return userService.getUserDetailsById(id);
    }


    @PutMapping
    public Boolean updateCommunity(@RequestBody User user){
        return userService.updateAccountDetails(user);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUserByUserId(@PathVariable int id){
        return userService.deleteUserAccountById(id);
    }

    @PostMapping("/forgotPassword")
    public int createVerificationCode(@RequestParam String email){
        return userService.createVerificationCode(email);
    }

    @PutMapping("/resetPassword")
    public Boolean updateUserPassword(@RequestBody User user)
    {
        return userService.updateUserPassword(user);
    }

}
