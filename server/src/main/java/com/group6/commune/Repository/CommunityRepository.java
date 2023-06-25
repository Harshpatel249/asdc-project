package com.group6.commune.Repository;

import com.group6.commune.Model.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
@Qualifier("CommunityRepository")
public class CommunityRepository implements ICommunityRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Boolean createCommunity(Community community) {
        String query = "INSERT INTO community (community_id, created_by, name, description, display_image) VALUES(?,?,?,?,?)";

        // Executing query
        int res = jdbcTemplate.update(query, new Object[]{community.getCommunity_id(), community.getCreated_by(), community.getName(), community.getDescription(), community.getDisplay_image()});

        if(res == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Community getCommunity(int communityID) {
        String query = "SELECT * FROM community WHERE community.community_id=?";

        return jdbcTemplate.queryForObject(query, new Object[]{communityID}, new CommunityRowMapper());
    }

    @Override
    public Boolean updateCommunity(Community community) {
        String query = "UPDATE community SET community_id = ?, created_by = ?, name = ?, description = ?, display_image = ? WHERE community_id = ?";
        int res = jdbcTemplate.update(query, new Object[]{community.getCommunity_id(), community.getCreated_by(), community.getName(), community.getDescription(), community.getDisplay_image(), community.getCommunity_id()});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean deleteCommunity(int communityID) {
        String query = "DELETE FROM community WHERE community_id=?";

        int res = jdbcTemplate.update(query, new Object[]{communityID});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Community> getAllCommunity() {
        String query =  "SELECT * FROM community";
        return jdbcTemplate.query(query, new CommunityRowMapper());
    }

    @Override
    public List<Community> getAllCommunity(String keyword) {
        String query = "SELECT * FROM community WHERE community.name LIKE \"" + keyword +"\"";
        return jdbcTemplate.query(query, new CommunityRowMapper());
    }
}
