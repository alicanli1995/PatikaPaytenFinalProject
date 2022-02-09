package com.patikafinal.graduationproject.controller.request;


import com.patikafinal.graduationproject.repository.models.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberCreateRequest {

    @NotBlank
    private String tcNo;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private Long salary;

    @NotBlank
    private String phoneNumber;

    public Member convertToMember() {
        return Member.builder()
                .tcNo(tcNo)
                .name(name)
                .lastName(lastName)
                .salary(salary)
                .phoneNumber(phoneNumber)
                .build();
    }

}
