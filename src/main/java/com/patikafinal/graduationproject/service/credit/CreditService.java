package com.patikafinal.graduationproject.service.credit;

import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;

import java.util.List;

public interface CreditService {
    Credit getCreditLimit(Member member);
    List<Credit> findAll();
    CreditEntity retrieve(String  tcNo);
    Credit update(CreditEntity creditEntity);
}
