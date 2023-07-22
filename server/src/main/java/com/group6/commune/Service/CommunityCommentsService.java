package com.group6.commune.Service;

import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Model.CommunityPosts;

public interface CommunityCommentsService {
    CommunityComments deleteComment(int id);
    CommunityComments createComment(CommunityComments comments);
}
