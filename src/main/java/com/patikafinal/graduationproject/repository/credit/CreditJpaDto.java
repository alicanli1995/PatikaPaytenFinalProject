package com.patikafinal.graduationproject.repository.credit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditJpaDto extends JpaRepository<CreditEntity,Long> {
    void deleteAllByTcNo(String tcNo);
    Optional<CreditEntity> findByTcNo(String tcNo);
}
