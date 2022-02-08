package com.patikafinal.graduationproject.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GetCreditRequest {
    @NotBlank
    private String tcNo;
}


