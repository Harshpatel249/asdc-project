package com.group6.commune.Service;


import com.group6.commune.AppLogger.AppLogger;
import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Repository.InterestRepositoryImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class InterestServiceImpl implements InterestService{
    @Autowired
    private InterestRepositoryImpl interestRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger log = AppLogger.getLogger();

    @Override
    public List<Interest> getInterestList() {
        List<Interest> events = interestRepository.getInterestList();
        if (events.isEmpty()){
            throw new DataNotFoundException("Interest list is empty.");
        }
        return interestRepository.getInterestList();
    }

    @Override
    public boolean addUserInterest(UserInterests userInterests, BindingResult result) {
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
        return true;
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
