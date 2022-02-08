package com.patikafinal.graduationproject.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MemberDeleteRequest {
    @NotNull
    private Long id;
}
