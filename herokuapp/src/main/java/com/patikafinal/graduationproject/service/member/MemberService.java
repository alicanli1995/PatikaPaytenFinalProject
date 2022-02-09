package com.patikafinal.graduationproject.service.member;

import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface MemberService {
    Member save(Member member);
    List<Member> findAll();
    void update(MemberEntity memberEntity);
    Member retrieve(Long id);
    void delete(Long id);
    Member retrieveByTcNo(String tcNo);
    boolean contains(String tcNo);
}
