package com.group6.commune.Model;

import java.util.Date;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class CommunityPosts {
    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int postId;

    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int UserId;

    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int CommunityId;

    @NotNull (message = "Post title should not be null")
    @NotEmpty (message = "Post title should not be empty")
    private String postTitle;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCommunityId() {
        return CommunityId;
    }

    public void setCommunityId(int communityId) {
        CommunityId = communityId;
    }

    @NotNull (message = "Post description should not be null")
    @NotEmpty(message = "Post description should not be empty")
    private String description;


    @NotNull (message = "Event location should not be null")
    @NotEmpty (message = "Event location should not be empty")
    private String location;

   private String postImage;

    public CommunityPosts(int postId, int userId, int communityId, String postTitle, String description, String location, String postImage) {
        this.postId = postId;
        UserId = userId;
        CommunityId = communityId;
        this.postTitle = postTitle;
        this.description = description;
        this.location = location;
        this.postImage = postImage;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public CommunityPosts(){

    }


}

