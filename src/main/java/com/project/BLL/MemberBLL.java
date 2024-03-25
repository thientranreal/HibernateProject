package com.project.BLL;

import com.project.DAL.MemberDAL;
import com.project.DAL.MemberDAL;
import com.project.Entity.Handle;
import com.project.Entity.Member;

import java.util.List;

public class MemberBLL {
    private final MemberDAL memberDAL;

    public MemberBLL() {
        memberDAL = new MemberDAL();
    }

    // Insert into
    public void createMember(Member member) {
        // Validate the Member object here
        // Apply business rules here
//        memberDAL.createMember(member);
    }

    // Update
    public void updateMember(Member member) {
        // Validate the Member object here
        // Apply business rules here
//        memberDAL.updateMember(member);
    }

    // Delete
    public void deleteMember(int id) {
        // Validate the id here
        // Apply business rules here
//        memberDAL.deleteMember(id);
    }

    // Read
    public Member getMemberByID(int id) {
        // Validate the id here
        // Apply business rules here
        return memberDAL.getMember(id);
    }

    public List<Member> getAllMembers() {
        return memberDAL.listMember();
    }
}
