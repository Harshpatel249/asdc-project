package com.group6.commune.Repository;

import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Model.Member;

import java.util.List;

public interface IMemberRepository {
    public Boolean addMember(Member member);
    public List<Member> getAllMembers(int communityID);
    public Boolean deleteMember(Member member);
    public Boolean changeUserRole(Member member, UserRoles newRole);
}
