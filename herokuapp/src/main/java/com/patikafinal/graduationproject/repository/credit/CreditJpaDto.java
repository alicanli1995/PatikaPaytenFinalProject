package com.patikafinal.graduationproject.repository.credit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditJpaDto extends JpaRepository<CreditEntity,Long> {
    void deleteAllByTcNo(String tcNo);
}
