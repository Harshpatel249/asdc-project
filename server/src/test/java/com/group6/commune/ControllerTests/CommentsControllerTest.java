package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.CommentsController;
import com.group6.commune.Controller.PostController;
import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Repository.CommunityCommentsImpl;
import com.group6.commune.Service.CommunityCommentsServiceImpl;
import com.group6.commune.Service.CommunityPostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class CommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommunityCommentsServiceImpl commentsService;

    @InjectMocks
    private CommentsController commentsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentsController).build();
    }

    @Test
    public void getCommentByIDTest() throws Exception {
        CommunityComments comments = new CommunityComments();
        comments.setCommentId(1);
        comments.setComment("Test Comment");
        given(commentsService.getCommentsById(1)).willReturn(comments);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.comment").value("Test Comment"));
    }

//    @Test
//    public void getCommentByIdWhichDoesNotExistTest() throws Exception {
//        given(commentsService.getCommentsById(1)).willThrow(new DataNotFoundException("Comment with ID: 1 not found"));
//
//        mockMvc.perform(get("/comments/1"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value("Comment with ID: 1 not found"));
//    }

    @Test
    public void createCommentTest() throws Exception {
        CommunityComments comments = new CommunityComments();
        comments.setCommentId(1);
        comments.setComment("Test Comment");

        given(commentsService.createComment(any(CommunityComments.class), any(BindingResult.class))).willReturn(comments);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"commentId\":1,\"comment\":\"Test Comment\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.comment").value("Test Comment"));
    }

//    @Test
//    public void createEventForInvalidDataTest() throws Exception {
//        CommunityComments comments = new CommunityComments();
//        comments.setCommentId(1);
//        comments.setComment("");
//
//        Map<String, String> errors = new HashMap<>();
//        errors.put("comment", "Comment should not be empty or null.");
//
//        given(commentsService.createComment(any(CommunityComments.class), any(BindingResult.class)))
//                .willThrow(new ValidationException("Validation failed", errors));
//
//        mockMvc.perform(post("/comments")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"commentId\":1,\"comment\":\"\"}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value("Validation failed"))
//                @Test
////    public void deleteCommentForRecordDoesNotExistTest() throws Exception {
////        given(commentsService.deleteComment(1)).willThrow(new DataNotFoundException("Comment with ID: 1 not found"));
////
////        mockMvc.perform(delete("/comments/1"))
////                .andExpect(status().isNotFound())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.message").value("Comment with ID: 1 not found"));
////    } .andExpect(jsonPath("$.errors.comment").value("Comment should not be empty or null."));
//    }

//

}
