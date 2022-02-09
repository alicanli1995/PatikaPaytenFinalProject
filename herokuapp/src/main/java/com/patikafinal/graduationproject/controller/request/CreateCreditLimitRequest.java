package com.patikafinal.graduationproject.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCreditLimitRequest {
    @NotBlank
    private String  tcNo;

}
