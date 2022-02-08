package com.patikafinal.graduationproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface MemberJpaDto extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByTcNo(String tcNo);

}
