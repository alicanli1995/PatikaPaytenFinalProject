package com.patikafinal.graduationproject.service.member;

import com.patikafinal.graduationproject.controller.request.MemberEditRequest;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface MemberService {
    Member save(Member member);
    List<Member> findAll();
    Member update(Long id, MemberEditRequest memberEditRequest);
    Member retrieve(Long id);
    void delete(Long id);
    Member retrieveByTcNo(String tcNo);
    boolean contains(String tcNo);
}
