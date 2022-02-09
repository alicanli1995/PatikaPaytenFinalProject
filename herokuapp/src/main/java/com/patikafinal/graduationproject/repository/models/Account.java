package com.patikafinal.graduationproject.repository.models;

import com.patikafinal.graduationproject.repository.account.AccountEntity;
import com.patikafinal.graduationproject.repository.role.RoleEntity;
import lombok.*;

import java.util.Collection;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Account {

    private Long id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;

    private Collection<RoleEntity> roles;

    public static Account convertFromEntity(AccountEntity accountEntity)
    {
        return Account.builder()
                .id(accountEntity.getId())
                .firstName(accountEntity.getFirstName())
                .lastName(accountEntity.getLastName())
                .mail(accountEntity.getMail())
                .password(accountEntity.getPassword())
                .roles(accountEntity.getRoles())
                .build();
    }

    public AccountEntity convertToAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setFirstName(getFirstName());
        accountEntity.setLastName(getLastName());
        accountEntity.setMail(getMail());
        accountEntity.setPassword(getPassword());
        return accountEntity;
    }


}
