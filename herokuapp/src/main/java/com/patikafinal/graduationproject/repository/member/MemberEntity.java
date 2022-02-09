package com.patikafinal.graduationproject.repository.member;


import com.patikafinal.graduationproject.repository.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "member",uniqueConstraints = @UniqueConstraint(columnNames = "tcno"))
@Entity(name = "member")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    //It's random between 0-2000 value. It's can be changed on web edit page.
    @Column(nullable = false)
    private int creditScore;

    @Column(name = "tcno", nullable = false, updatable = false)
    private String tcNo;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Long salary;

    @Column(nullable = false)
    private String phoneNumber;



}
