package com.group6.commune.Repository;

import com.group6.commune.Mapper.InterestRowMapper;
import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InterestRepositoryImpl implements IInterestRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Interest> getInterestList() {
        var query = """
                    SELECT * FROM interests;
                """;
        return jdbcTemplate.query(query, new InterestRowMapper());
    }

    @Override
    public void saveUserInterest(int userId, int interestId) {
        var query = "INSERT INTO users_interests (user_id, interest_id) VALUES (?, ?)";
        jdbcTemplate.update(query, userId, interestId);
    }
}
