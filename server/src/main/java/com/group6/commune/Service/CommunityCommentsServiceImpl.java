package com.group6.commune.Service;

import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Repository.CommunityCommentsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityCommentsServiceImpl implements CommunityCommentsService{

   @Autowired
   CommunityCommentsImpl communityComments;

    @Override
    public CommunityComments deleteComment(int id) {
        return communityComments.deleteComment(id);
    }

    @Override
    public CommunityComments createComment(CommunityComments comments) {
        return communityComments.createComment(comments);
    }
}
