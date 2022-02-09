package com.patikafinal.graduationproject.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaDto extends JpaRepository<AccountEntity,Long> {
    Optional<AccountEntity> findByMail(String mail);
}
