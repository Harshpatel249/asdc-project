package com.group6.commune.Repository;

import com.group6.commune.Mapper.InterestRowMapper;
import com.group6.commune.Model.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InterestRepositoryImpl implements InterestRepository {
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
    public boolean saveUserInterest(int userId, int interestId) {
        var query = "INSERT INTO users_interests (user_id, interest_id) VALUES (?, ?)";

        int res = jdbcTemplate.update(query, userId, interestId);
        if(res == 1){
            return true;
        }else{
            return false;
        }
    }
}
