package com.patikafinal.graduationproject.service.member;

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
    public List<Member> findAll()  {
        return memberDto
                .findAll()
                .stream()
                .map(Member::convertFromEntity)
                .toList();
    }

    @Override
    public void update(MemberEntity memberEntity) {
        MemberEntity member = memberDto.getByIdEntities(memberEntity.getId());
        member.setId(memberEntity.getId());
        member.setCreditScore(memberEntity.getCreditScore());
        member.setTcNo(memberEntity.getTcNo());
        member.setName(memberEntity.getName());
        member.setLastName(memberEntity.getLastName());
        member.setSalary(memberEntity.getSalary());
        member.setPhoneNumber(memberEntity.getPhoneNumber());
        memberDto.save(member);
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
