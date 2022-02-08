package com.patikafinal.graduationproject.service.member;

import com.patikafinal.graduationproject.controller.request.MemberEditRequest;
import com.patikafinal.graduationproject.repository.credit.CreditDto;
import com.patikafinal.graduationproject.repository.member.MemberDto;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.models.Member;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDto memberDto;
    private final CreditDto creditDto;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = member.convertToMemberEntity();
        memberEntity.setCreditScore((int) (Math.random()*2000));
        return memberDto.save(memberEntity);
    }

    @Override
    @Cacheable(cacheNames = "memberCache")
    public List<Member> findAll()  {
        return memberDto
                .findAll()
                .stream()
                .map(Member::convertFromEntity)
                .toList();
    }

    @Override
    public Member update(Long id , MemberEditRequest memberEditRequest) {
        MemberEntity member = memberDto.getByIdEntities(id);
        member.setId(member.getId());
        member.setCreditScore(member.getCreditScore());
        member.setTcNo(member.getTcNo());
        member.setName(memberEditRequest.getName());
        member.setLastName(memberEditRequest.getLastName());
        member.setSalary(memberEditRequest.getSalary());
        member.setPhoneNumber(memberEditRequest.getPhoneNumber());
        return memberDto.save(member);
    }

    @Override
    public Member retrieve(Long id) {
        return memberDto.retrieveMember(id);
    }

    @Override
    public void delete(Long id) {
        Member retrieveMember = memberDto.retrieveMember(id);
        creditDto.deleteByMemberCredits(retrieveMember.getTcNo());
        memberDto.deleteById(id);
    }


    @Override
    public Member retrieveByTcNo(String  tcNo) {
        return memberDto.retrieveByTcNo(tcNo);
    }

    @Override
    public boolean contains(String tcNo) {
        return memberDto.contains(tcNo);
    }
}
