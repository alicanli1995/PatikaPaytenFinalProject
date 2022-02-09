package com.patikafinal.graduationproject.repository.credit;

import com.patikafinal.graduationproject.repository.models.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "credit")
@Entity(name = "credit")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditEntity extends BaseEntity {

    @Column(name = "tcno", nullable = false)
    private String  tcNo;

    @Column(nullable = false)
    private Double creditLimit;

    private Boolean creditStatus;


    private String completed;


}
