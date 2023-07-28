package com.group6.commune.Repository;
import com.group6.commune.Model.CommunityComments;

import java.util.List;


public interface CommunityCommentsRepo {
    CommunityComments createComment(CommunityComments comments);

    int deleteComment(int id);

    CommunityComments getCommentsById(int postid);

    List<CommunityComments> getCommentListById(int postid);
}
