package com.patikafinal.graduationproject.repository.credit;

import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface CreditDto {
    Credit getCredit(Double creditLimit, Boolean creditStatus, Member member);
    List<Credit> findAll();
    CreditEntity retrieve(String  tcNo);
    CreditEntity getByIdEntities(Long id);
    Credit save(CreditEntity entity);
    void deleteByMemberCredits(String  tcNo);
}
