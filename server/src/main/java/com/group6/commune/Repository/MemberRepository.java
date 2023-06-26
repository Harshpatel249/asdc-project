package com.group6.commune.Repository;

import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Mapper.MemberRowMapper;
import com.group6.commune.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("MemberRepository")
public class MemberRepository implements IMemberRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addMember(Member member) {
        String query = "INSERT INTO members (community_id, user_id, user_role) VALUES(?,?,?)";

        // Executing query
        int res = jdbcTemplate.update(query, new Object[]{member.getCommunity_id(), member.getUser_id(), member.getUser_role().toString()});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Member> getAllMembers(int communityID) {
        String query = "SELECT * FROM members WHERE members.community_id=" + communityID;
        return jdbcTemplate.query(query, new MemberRowMapper());
    }

    @Override
    public Boolean deleteMember(Member member) {
        String query = "DELETE FROM members WHERE community_id = ? AND user_id = ?";

        int res = jdbcTemplate.update(query, new Object[]{member.getCommunity_id(), member.getUser_id()});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean changeUserRole(Member member, UserRoles newRole) {
        String query = "UPDATE members SET user_role = ? WHERE community_id = ? AND user_id = ?";
        int res = jdbcTemplate.update(query, new Object[]{newRole.toString(), member.getCommunity_id(), member.getUser_id()});

        if(res == 1){
            return true;
        }else{
            return false;
        }
    }
}
