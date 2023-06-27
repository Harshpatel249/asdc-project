package com.group6.commune.Service;

import com.group6.commune.Model.User;
import com.group6.commune.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserDetailsById(int userId){
        return userRepository.getUserDetailsByID(userId);
    }

    @Override
    public Boolean createUser(User user){
        return userRepository.createUserAccount(user);
    }

    @Override
    public Boolean updateAccountDetails(User user)
    {
        return userRepository.updateAccountDetails(user);
    }

    @Override
    public Boolean deleteUserAccountById(int id)
    {
        return userRepository.deleteUserAccountById(id);
    }
}
