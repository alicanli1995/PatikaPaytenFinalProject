package com.patikafinal.graduationproject.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class MemberDeleteRequest {
    @NotNull
    private String tcNo;
}
