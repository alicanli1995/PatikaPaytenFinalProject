package com.patikafinal.graduationproject.repository.account;

import com.patikafinal.graduationproject.repository.models.Account;

public interface AccountDto {
    AccountEntity findByMail(String mail);
    Account saveMember(AccountEntity userEntity);
    boolean contains(String mail);
}
