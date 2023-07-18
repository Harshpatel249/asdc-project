package com.group6.commune.Service;

import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {
    @Autowired
    private InterestRepository interestRepository;


    public List<Interest> getInterestList() {
        return interestRepository.getInterestList();
    }

    public void addUserInterest(int userId, int interestId) {
        UserInterests userInterests = new UserInterests(userId, interestId);
        interestRepository.saveUserInterest(userInterests);
    }
}
