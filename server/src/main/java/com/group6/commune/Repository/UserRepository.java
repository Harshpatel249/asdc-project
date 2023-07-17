package com.group6.commune.Repository;

import com.group6.commune.Model.User;
import org.springframework.http.ResponseEntity;

public interface UserRepository {

    public ResponseEntity<User> getUserDetailsByID(int userId);

    public ResponseEntity<String> createUserAccount(User user);

    public ResponseEntity<String> updateAccountDetails(User user);
    public ResponseEntity<String> deleteUserAccountById(int id);

    public ResponseEntity<String> updatePassword(User user);

}
