package com.patikafinal.graduationproject.repository.member;

import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface MemberDto {
    Member save(MemberEntity member);
    List<MemberEntity> findAll();
    MemberEntity getByIdEntities(Long memberId);
    Member retrieveMember(Long id);
    void deleteById(Long id);
    Member retrieveByTcNo(String tcNo);
    boolean contains(String tcNo);
}
