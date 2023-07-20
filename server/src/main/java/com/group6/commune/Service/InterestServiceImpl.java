package com.group6.commune.Service;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Repository.IInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl {
    @Autowired
    private IInterestRepository interestRepository;


    public List<Interest> getInterestList() {
        List<Interest> events = interestRepository.getInterestList();
        if (events.isEmpty()){
            throw new DataNotFoundException("Interest list is empty.");
        }
        return interestRepository.getInterestList();
    }

    public void addUserInterest(int userId, int interestId) {
        UserInterests userInterests = new UserInterests(userId, interestId);
        interestRepository.saveUserInterest(userInterests);
    }

//    public void addUserInterestList(int userId, List<Integer> interestList) {
//        UserInterests userInterests = new UserInterests(userId, InterestList);
//        interestRepository.saveUserInterest(userInterests);
//    }
}
