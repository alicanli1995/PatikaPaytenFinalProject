package com.patikafinal.graduationproject.controller.request;


import com.patikafinal.graduationproject.repository.models.Account;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountRegisterRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String mail;

    @NotBlank
    private String password;

    public Account convertToMember() {
        return Account.builder()
                .firstName(firstName)
                .lastName(lastName)
                .mail(mail)
                .password(password)
                .build();
    }

}
