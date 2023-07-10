package com.group6.commune.Controller;

import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Model.Member;
import com.group6.commune.Service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community/{community_id}/members")
public class MemberController {
    @Autowired
    @Qualifier("MemberService")
    private IMemberService memberService;

    @PostMapping
    public Boolean addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMember(@PathVariable int community_id){
        return memberService.getAllMembers(community_id);
    }

    @DeleteMapping
    public Boolean deleteMember(@RequestBody Member member){
        return memberService.deleteMember(member);
    }

    @PutMapping
    public Boolean changeUserRole(@RequestBody Member member,@RequestParam(name = "new_role") String new_role){
        UserRoles newRole = new_role.equalsIgnoreCase(UserRoles.Member.toString())?UserRoles.Member:UserRoles.Admin;
        return memberService.changeUserRole(member, newRole);
    }
}
