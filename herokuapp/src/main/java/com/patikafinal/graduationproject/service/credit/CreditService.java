package com.patikafinal.graduationproject.service.credit;

import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface CreditService {
    Double getCreditLimit(Member member);
    List<Credit> findAll();
    CreditEntity retrieve(Long id);
    void update(CreditEntity creditEntity);
}
