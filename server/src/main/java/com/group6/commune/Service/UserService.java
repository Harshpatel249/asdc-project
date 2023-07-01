package com.group6.commune.Service;

import com.group6.commune.Model.User;

public interface UserService {
    public User getUserDetailsById(int userId);

    public Boolean createUser(User user);

    public Boolean updateAccountDetails(User user);

    public Boolean deleteUserAccountById(int userId);

    public int createVerificationCode(String email);

    public Boolean updateUserPassword(User user);

}
