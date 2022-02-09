package com.patikafinal.graduationproject.service.credit;

import com.patikafinal.graduationproject.repository.credit.CreditDto;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    private static final Double CREDIT_LIMIT_HIT = 4.0;
    private final CreditDto creditDto;

    @Override
    public Double getCreditLimit(Member member) {
        Double creditLimit = creditLimitCalc(member);
        Boolean creditStatus = creditStatusQuery(member);
        return creditDto.getCredit(creditLimit,creditStatus,member);
    }

    @Override
    public List<Credit> findAll() {
        return creditDto.findAll();
    }

    @Override
    public CreditEntity retrieve(Long id) {
        return creditDto.retrieve(id);
    }

    @Override
    public void update(CreditEntity creditEntity) {
        CreditEntity entity = creditDto.getByIdEntities(creditEntity.getId());
        entity.setId(entity.getId());
        entity.setCreditStatus(entity.getCreditStatus());
        entity.setCreditLimit(entity.getCreditLimit());
        entity.setTcNo(entity.getTcNo());
        entity.setCreatedDate(entity.getCreatedDate());
        entity.setCompleted(entity.getCompleted());
        creditDto.save(entity);
    }

    private Boolean creditStatusQuery(Member member) {
        return member.getCreditScore() > 500;
    }

    private Double creditLimitCalc(Member member) {
        if(member.getCreditScore() < 500){
            return 0.00;
        }
        else if (member.getCreditScore() >= 500 && member.getCreditScore() < 1000 )
        {
            if(member.getSalary() < 5000){
                return 10000.0;
            }
            else
            {
                return 20000.0;
            }
        }
        else{
            return member.getSalary() * CREDIT_LIMIT_HIT;
        }
    }
}
