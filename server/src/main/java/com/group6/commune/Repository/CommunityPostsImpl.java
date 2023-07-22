package com.group6.commune.Repository;

import com.group6.commune.Mapper.PostMapper;
import com.group6.commune.Model.CommunityPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityPostsImpl implements CommunityPost {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public CommunityPosts createPosts(CommunityPosts posts) {

        String query = """
                INSERT INTO posts (post_id, user_id, community_id, title, description, post_image) VALUES(?,?,?,?,?,?)""";
        int result = jdbcTemplate.update(query,
                        posts.getPostId(),
                        posts.getUserId(),
        posts.getCommunityId(),
        posts.getPostTitle(),
        posts.getDescription(),
        posts.getPostImage());
        
        return result ==1 ? posts : new CommunityPosts();
    }

    @Override
    public CommunityPosts updatePosts(CommunityPosts posts) {

        String query= """
                  UPDATE posts
                  SET
                      title = ?,
                      description = ?,
                      post_image = ?
                  WHERE
                      post_id = ?;
                """;

        int res = jdbcTemplate.update(query,
                posts.getPostTitle(),
                posts.getDescription(),
                posts.getPostImage(),
                posts.getPostId());

        return res == 1 ? posts : new CommunityPosts();

    }

    @Override
    public CommunityPosts deletePosts(int id) {
        String query = """
                    DELETE FROM posts WHERE post_id = ?;
                """;
        int res = jdbcTemplate.update(query, id);
        return new CommunityPosts();


    }

    @Override
    public CommunityPosts getPostById(int id) {
        String query = "SELECT * FROM posts where post_id=?";
        CommunityPosts event = jdbcTemplate.queryForObject(query, new Object[]{id},new PostMapper());
        System.out.println("event: "+ event);
        return event == null ? new CommunityPosts() : event;
    }

    @Override
    public List<CommunityPosts> getAllPosts() {
        var query = """
                    select * from posts;
                """;
        return jdbcTemplate.query(query,new PostMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
