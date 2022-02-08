package com.patikafinal.graduationproject.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class MemberEditRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private Long salary;

    @NotBlank
    private String phoneNumber;
}
