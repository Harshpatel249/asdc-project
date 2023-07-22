package com.group6.commune.Repository;
import com.group6.commune.Model.CommunityComments;


public interface CommunityCommentsRepo {
    CommunityComments createComment(CommunityComments comments);

    CommunityComments deleteComment(int id);
}
