package com.patikafinal.graduationproject.repository.credit;

import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface CreditDto {
    Double getCredit(Double creditLimit, Boolean creditStatus, Member member);
    List<Credit> findAll();
    CreditEntity retrieve(Long id);
    CreditEntity getByIdEntities(Long id);
    void save(CreditEntity entity);
    void deleteByMemberCredits(String  tcNo);
}
