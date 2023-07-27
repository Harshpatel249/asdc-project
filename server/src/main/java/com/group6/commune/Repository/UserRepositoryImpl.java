package com.group6.commune.Repository;

import com.group6.commune.Mapper.UserRowMapper;
import com.group6.commune.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User getUserDetailsByID(int userId)
    {

        String query = "SELECT * FROM users WHERE user_id=?";

        return jdbcTemplate.queryForObject(query, new Object[]{userId}, new UserRowMapper());
    }

    String query = "INSERT INTO community (community_id, created_by, name, description, display_image) VALUES(?,?,?,?,?)";

    @Override
    public Boolean createUserAccount(User user) {
        String query = "INSERT INTO users (user_id, first_name, last_name, dob, gender, email, " +
                "contact, password, profile_pic, enrollment_date) VALUES(?,?,?,?,?,?,?,?,?,?)";

        // Executing query
        int res = jdbcTemplate.update(query, new Object[]{user.getUserId(), user.getFirstName(), user.getLastName(), user.getDob(), user.getGender(), user.getEmail(),
                user.getContact(), user.getPassword(), user.getProfilePic(), user.getEnrollmentDate()});

        if(res == 1){
            return true;
        }else{
            return new ResponseEntity<>("User account not created", HttpStatus.BAD_REQUEST);
        }
    }

        @Override
        public Boolean updateAccountDetails(User user) {
            String query = "UPDATE users SET user_id = ?, first_name = ?, last_name = ?, dob = ?, gender = ?, email = ?," +
                    " contact = ?, password = ?, profile_pic = ?,enrollment_date = ?  WHERE user_id = ?";
            int res = jdbcTemplate.update(query, new Object[]{user.getUserId(), user.getFirstName(), user.getLastName(), user.getDob(), user.getGender(), user.getEmail(),
                    user.getContact(), user.getPassword(), user.getProfilePic(), user.getEnrollmentDate(), user.getUserId()});

            if(res == 1){
                return true;
            }else{
                return false;
            }
        }

    public Boolean deleteUserAccountById(int id)
    {
        String query = "DELETE FROM users WHERE user_id=?";

        int res = jdbcTemplate.update(query, new Object[]{id});

        if(res == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Boolean updatePassword(User user) {
        String query = "UPDATE users SET password = ?  WHERE email = ?";
        int res = jdbcTemplate.update(query, new Object[]{user.getPassword(), user.getEmail()});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }


}
