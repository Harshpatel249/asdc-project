package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.InterestController;
import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Service.InterestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;

@WebMvcTest(InterestController.class)
@AutoConfigureMockMvc
class InterestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InterestService interestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInterestList() throws Exception {
        Interest interest1 = new Interest(1,"Music", "Music");
        Interest interest2 = new Interest(2,"Dance", "Dance");
        List<Interest> expectedInterestList = Arrays.asList(interest1, interest2);

        when(interestService.getInterestList()).thenReturn(expectedInterestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/interest")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].interestId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].interestId").value(2));

        verify(interestService, times(1)).getInterestList();
   }

    @Test
    void addUserInterest() throws Exception {
        int userId = 1;
        int interestId = 1;
        UserInterests userInterests = new UserInterests(interestId, userId);

        mockMvc.perform(MockMvcRequestBuilders.post("/interest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"interestId\": 1, \"userId\": 1}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(interestService, times(1)).addUserInterest(userInterests.getUserId(), userInterests.getInterestId());
    }
}