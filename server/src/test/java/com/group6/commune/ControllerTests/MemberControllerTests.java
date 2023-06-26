package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.MemberController;
import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Model.Member;

import com.group6.commune.Service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMember() throws Exception {
        Member member = new Member(1, 1, UserRoles.Member);

        when(memberService.addMember(any(Member.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/community/1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"community_id\": 1, \"user_id\": 1, \"user_role\": \"Member\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("true", response);
    }

    @Test
    public void testGetAllMember() throws Exception {
        Member member1 = new Member(1, 1, UserRoles.Member);
        Member member2 = new Member(1, 2, UserRoles.Admin);
        List<Member> expectedMembers = Arrays.asList(member1, member2);

        when(memberService.getAllMembers(1)).thenReturn(expectedMembers);

        mockMvc.perform(MockMvcRequestBuilders.get("/community/1/members")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].user_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].user_id").value(2));

        verify(memberService, times(1)).getAllMembers(1);
    }

    @Test
    public void testDeleteMember() throws Exception {
        Member member = new Member(1, 1, UserRoles.Member);

        when(memberService.deleteMember(any(Member.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/community/1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"community_id\": 1, \"user_id\": 1, \"user_role\": \"Member\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("true", response);
    }

    @Test
    public void testChangeUserRole() throws Exception {
        Member member = new Member(1, 1, UserRoles.Member);
        String newRole = "Admin";

        when(memberService.changeUserRole(any(Member.class), any(UserRoles.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/community/1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"community_id\": 1, \"user_id\": 1, \"user_role\": \"Member\"}")
                        .param("new_role", newRole)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("true", response);
        verify(memberService, times(1)).changeUserRole(any(Member.class), any(UserRoles.class));
    }
}
