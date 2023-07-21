package com.group6.commune.Service;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Repository.IInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl{
    @Autowired
    private IInterestRepository interestRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Interest> getInterestList() {
        List<Interest> events = interestRepository.getInterestList();
        if (events.isEmpty()){
            throw new DataNotFoundException("Interest list is empty.");
        }
        return interestRepository.getInterestList();
    }

    public void addUserInterest(UserInterests userInterests) {
        if (!isUserIdExists(userInterests.getUserId())) {
            for (Integer interestId : userInterests.getInterestIds()) {
                if (!isInterestIdExists(interestId)) {
                    throw new IllegalArgumentException("Invalid interest_id: " + interestId);
                }
            }
            throw new IllegalArgumentException("Invalid user_id. User does not exist.");
        }
        for (int interestId : userInterests.getInterestIds()) {
            interestRepository.saveUserInterest(userInterests.getUserId(), interestId);
        }
    }

    private boolean isUserIdExists(int userId) {
        String query = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, userId);
        return count > 0;
    }

    private boolean isInterestIdExists(int interestId) {
        String query = "SELECT COUNT(*) FROM interests WHERE interest_id = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, interestId);
        return count > 0;
    }
}
