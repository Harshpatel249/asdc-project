package com.group6.commune.Repository;

import com.group6.commune.Model.User;

public interface UserRepository {

    public User getUserDetailsByID(int userId);

    public Boolean createUserAccount(User user);

    public Boolean updateAccountDetails(User user);
    public Boolean deleteUserAccountById(int id);

    public Boolean updatePassword(User user);

}
