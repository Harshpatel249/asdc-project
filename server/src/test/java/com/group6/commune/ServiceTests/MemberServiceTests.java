package com.group6.commune.ServiceTests;

import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Model.Member;
import com.group6.commune.Repository.IMemberRepository;
import com.group6.commune.Service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceTests {

    @Mock
    private IMemberRepository memberRepository;

    @Autowired
    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testAddMember() {
        Member member = new Member(1, 1, UserRoles.Member);

        when(memberRepository.addMember(any(Member.class))).thenReturn(true);

        boolean result = memberService.addMember(member);

        assertTrue(result);
        verify(memberRepository, times(1)).addMember(member);
    }

    @Test
    public void testGetAllMembers() {
        int communityID = 1;
        List<Member> expectedMembers = new ArrayList<>();
        expectedMembers.add(new Member(1, 1, UserRoles.Member));
        expectedMembers.add(new Member(1, 2, UserRoles.Admin));

        when(memberRepository.getAllMembers(communityID)).thenReturn(expectedMembers);

        List<Member> result = memberService.getAllMembers(communityID);

        assertEquals(expectedMembers, result);
        verify(memberRepository, times(1)).getAllMembers(communityID);
    }

    @Test
    public void testDeleteMember() {
        Member member = new Member(1, 1, UserRoles.Member);

        when(memberRepository.deleteMember(any(Member.class))).thenReturn(true);

        boolean result = memberService.deleteMember(member);

        assertTrue(result);
        verify(memberRepository, times(1)).deleteMember(member);
    }

    @Test
    public void testChangeUserRole() {
        Member member = new Member(1, 1, UserRoles.Member);
        UserRoles newRole = UserRoles.Admin;

        when(memberRepository.changeUserRole(any(Member.class), any(UserRoles.class))).thenReturn(true);

        boolean result = memberService.changeUserRole(member, newRole);

        assertTrue(result);
        verify(memberRepository, times(1)).changeUserRole(member, newRole);
    }
}
