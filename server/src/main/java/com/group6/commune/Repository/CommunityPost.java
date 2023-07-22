package com.group6.commune.Repository;


import com.group6.commune.Model.CommunityPosts;

import java.util.List;

public interface CommunityPost {
    CommunityPosts createPosts(CommunityPosts posts);
    CommunityPosts updatePosts(CommunityPosts posts);

    CommunityPosts deletePosts(int id);

    CommunityPosts getPostById(int id);

    List<CommunityPosts> getAllPosts();

}
