package com.patikafinal.graduationproject.repository.member;


import com.patikafinal.graduationproject.repository.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "member",uniqueConstraints = @UniqueConstraint(columnNames = "tcno"))
@Entity(name = "member")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    //It's random between 0-2000 value. It's can be changed on credit api endpoint.
    @Column(nullable = false)
    private int creditScore;

    @Column(name = "tcno", nullable = false, updatable = false)
    @Length(min = 11,max = 11)
    private String tcNo;

    @Length(min = 3 , max = 50)
    @Column(nullable = false)
    private String name;

    @Length(min = 3 , max = 50)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Long salary;

    @Length(min = 11,max = 11)
    @Column(nullable = false)
    private String phoneNumber;



}
