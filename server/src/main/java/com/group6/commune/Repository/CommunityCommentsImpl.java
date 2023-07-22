package com.group6.commune.Repository;

import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Model.CommunityPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityCommentsImpl implements CommunityCommentsRepo{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public CommunityComments createComment(CommunityComments comments) {
        String query = """
                INSERT INTO comments(commentId, postId, userId, comment, comment_date) VALUES(?,?,?,?,?,?)""";
        int result = jdbcTemplate.update(query,
                comments.getPostId(),
                comments.getUserId(),
                comments.getComment(),
                comments.getCommentDate(),
                comments.getCommentId()
                );

        return result ==1 ? comments : new CommunityComments();
    }

    @Override
    public CommunityComments deleteComment(int id) {
        String query = """
                    DELETE FROM comments WHERE comment_id = ?;
                """;
        int res = jdbcTemplate.update(query, id);
        return new CommunityComments();
    }
}
