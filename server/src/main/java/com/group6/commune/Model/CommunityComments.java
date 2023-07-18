package com.group6.commune.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CommunityComments {
    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int commentId;

    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int postId;

    public CommunityComments() {
    }

    public int getCommentId() {
        return commentId;
    }

    public CommunityComments(int commentId, int postId, int userId, String comment, Date commentDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int userId;

    @NotNull (message = "Comment name should not be null")
    @NotEmpty(message = "Comment should not be empty")
    private String comment;

    @NotNull (message = "Comment time should not be null")
    @NotEmpty (message = "Comment time should not be empty")
    private Date commentDate;
}
