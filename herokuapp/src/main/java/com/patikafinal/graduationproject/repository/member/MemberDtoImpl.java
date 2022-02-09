package com.patikafinal.graduationproject.repository.member;

import com.patikafinal.graduationproject.exception.MemberNotFoundException;
import com.patikafinal.graduationproject.repository.models.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.patikafinal.graduationproject.exception.ExceptionType.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberDtoImpl implements MemberDto{

    private final MemberJpaDto memberJpaDto;

    @Override
    public Member save(MemberEntity member) {
        memberJpaDto.save(member);
        return Member.convertFromEntity(memberJpaDto.save(member));
    }

    @Override
    public List<MemberEntity> findAll() {
        return memberJpaDto.findAll();
    }

    @Override
    public MemberEntity getByIdEntities(Long memberId) {
        return memberJpaDto.getById(memberId);
    }

    @Override
    public Member retrieveMember(Long id) {
        Optional<MemberEntity> memberEntity = (memberJpaDto.findById(id));
        return memberEntity.map(Member::convertFromEntity)
                .orElseThrow(() -> new MemberNotFoundException
                        (ACCOUNT_NOT_FOUND, "Member id:" + id));
    }

    @Override
    public void deleteById(Long id) {
        memberJpaDto.deleteById(id);
    }

    @Override
    public Member retrieveByTcNo(String  tcNo) {
        return memberJpaDto.findByTcNo(tcNo)
                .map(Member::convertFromEntity)
                .orElseThrow(() -> new MemberNotFoundException(ACCOUNT_NOT_FOUND, "Member id:" + tcNo));
    }

    @Override
    public boolean contains(String tcNo) {
        Optional<MemberEntity> userEntityOptional = (memberJpaDto.findByTcNo(tcNo));
        return userEntityOptional.isPresent();
    }
}
