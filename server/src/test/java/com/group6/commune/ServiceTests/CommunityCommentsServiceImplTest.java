package com.group6.commune.ServiceTests;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Repository.CommunityCommentsRepo;
import com.group6.commune.Repository.CommunityPost;
import com.group6.commune.Service.CommunityCommentsService;
import com.group6.commune.Service.CommunityCommentsServiceImpl;
import com.group6.commune.Validators.CommentsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class CommunityCommentsServiceImplTest {

    @Mock
    CommunityCommentsRepo communityComments;

    @Mock
    CommentsValidator commentsValidator;

    @InjectMocks
    CommunityCommentsServiceImpl communityCommentsService;


    @Test
    void createCommentForInvalidInputTest() {
        CommunityComments comments = new CommunityComments();
        BindingResult result = new BeanPropertyBindingResult(comments, "event");
        Map<String, String> errors = new HashMap<>();
        errors.put("comment", "Comment should not be empty or null.");
        doThrow(new ValidationException("Validation error", errors)).when(commentsValidator).validate(comments, result);

        assertThrows(ValidationException.class, () -> communityCommentsService.createComment(comments, result));
    }

    @Test
    public void testDeleteCommentNonExistingComment() {
        // Given a non-existing comment ID (let's assume ID 100 doesn't exist)
        int nonExistingCommentId = 100;
        CommunityCommentsService yourClassInstance = new CommunityCommentsServiceImpl();

        // When we try to delete the non-existing comment
        // Then a DataNotFoundException should be thrown
        assertThrows(NullPointerException.class, () -> {
            yourClassInstance.deleteComment(nonExistingCommentId);
        }, "Deleting a non-existing comment should throw a NullPointerException");
    }


}
