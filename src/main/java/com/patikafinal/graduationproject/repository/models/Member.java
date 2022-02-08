package com.patikafinal.graduationproject.repository.models;


import com.patikafinal.graduationproject.repository.member.MemberEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

    private Long id;
    private String tcNo;
    private String name;
    private String lastName;
    private Long salary;
    private String phoneNumber;
    private int creditScore;


    public static Member convertFromEntity(MemberEntity memberEntity)
    {
        return Member.builder()
                .id(memberEntity.getId())
                .tcNo(memberEntity.getTcNo())
                .name(memberEntity.getName())
                .lastName(memberEntity.getLastName())
                .salary(memberEntity.getSalary())
                .creditScore(memberEntity.getCreditScore())
                .phoneNumber(memberEntity.getPhoneNumber())
                .build();
    }

    public MemberEntity convertToMemberEntity() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(getId());
        memberEntity.setTcNo(getTcNo());
        memberEntity.setName(getName());
        memberEntity.setLastName(getLastName());
        memberEntity.setSalary(getSalary());
        memberEntity.setCreditScore(getCreditScore());
        memberEntity.setPhoneNumber(getPhoneNumber());
        return memberEntity;
    }



}
