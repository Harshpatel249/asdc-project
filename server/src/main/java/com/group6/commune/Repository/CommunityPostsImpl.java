package com.group6.commune.Repository;

import com.group6.commune.Model.CommunityPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CommunityPostsImpl implements CommunityPost {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public CommunityPosts createPosts(CommunityPosts posts) {

        String query = """
                INSERT INTO posts(postId, userId, communityId, title, description, post_image) VALUES(?,?,?,?,?,?)""";
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
        String query = String.format("""
                    SELECT * FROM post where post_id= %d"""+id);
//        CommunityPosts communityPosts = (CommunityPosts) jdbcTemplate.query(query, new EventRowMapper());
//        return communityPosts == null ? new CommunityPosts() : communityPosts;
        return null;
    }

    @Override
    public List<CommunityPosts> getAllPosts() {
        return null;
    }

}
