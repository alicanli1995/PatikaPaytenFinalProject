package com.patikafinal.graduationproject.repository.account;

import com.patikafinal.graduationproject.exception.MemberNotFoundException;
import com.patikafinal.graduationproject.repository.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.patikafinal.graduationproject.exception.ExceptionType.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional

public class AccountDtoImpl implements AccountDto {

    private final AccountJpaDto accountJpaDto;

    @Override
    public AccountEntity findByMail(String mail) {
        Optional<AccountEntity> memberEntity = (accountJpaDto.findByMail(mail));
        return memberEntity
                .orElseThrow(() -> new MemberNotFoundException
                        (ACCOUNT_NOT_FOUND, "Account id:" + mail));
    }

    @Override
    public Account saveMember(AccountEntity userEntity) {
        AccountEntity accountEntity = accountJpaDto.save(userEntity);
        return Account.convertFromEntity(accountEntity);
    }

    @Override
    public boolean contains(String mail) {
        Optional<AccountEntity> userEntityOptional = (accountJpaDto.findByMail(mail));
        return userEntityOptional.isPresent();
    }

}
