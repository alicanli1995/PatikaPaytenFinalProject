package com.patikafinal.graduationproject.repository.role;

import com.patikafinal.graduationproject.repository.models.enums.ROLE;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter

@Entity(name = "role")
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)

public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ROLE roleUser;

    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();


}
