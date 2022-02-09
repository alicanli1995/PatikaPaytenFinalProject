package com.patikafinal.graduationproject.service.account;

import com.patikafinal.graduationproject.repository.account.AccountEntity;
import com.patikafinal.graduationproject.repository.models.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account saveMember(Account user);
    AccountEntity retrieveByMail(String getMemberName);
    boolean contains(String mail);
}
