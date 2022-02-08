package com.patikafinal.graduationproject.repository.credit;

import com.patikafinal.graduationproject.exception.ExceptionType;
import com.patikafinal.graduationproject.exception.MemberNotFoundException;
import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditDtoImpl implements CreditDto{

    private final CreditJpaDto creditJpaDto;

    @Override
    public Credit getCredit(Double creditLimit, Boolean creditStatus, Member member) {
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setCreditLimit(creditLimit);
        creditEntity.setCreditStatus(creditStatus);
        creditEntity.setTcNo(member.getTcNo());
        creditEntity.setCompleted("NO");
        return Credit.convertFromEntity(creditJpaDto.save(creditEntity));
    }

    @Override
    public List<Credit> findAll() {
        return creditJpaDto.findAll().stream()
                .map(Credit::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CreditEntity retrieve(String tcNo) {
        return creditJpaDto.findByTcNo(tcNo)
                .orElseThrow
                        (() -> new MemberNotFoundException(ExceptionType.ACCOUNT_NOT_FOUND, "The member is not in database " + tcNo));
    }

    @Override
    public CreditEntity getByIdEntities(Long id) {
        return creditJpaDto.getById(id);
    }

    @Override
    public Credit save(CreditEntity entity) {
       return Credit.convertFromEntity(creditJpaDto.save(entity));
    }

    @Override
    public void deleteByMemberCredits(String tcNo) {
        creditJpaDto.deleteAllByTcNo(tcNo);
    }
}
