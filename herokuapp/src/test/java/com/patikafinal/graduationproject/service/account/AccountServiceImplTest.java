package com.patikafinal.graduationproject.service.account;

import com.patikafinal.graduationproject.repository.account.AccountDto;
import com.patikafinal.graduationproject.repository.account.AccountEntity;
import com.patikafinal.graduationproject.repository.models.Account;
import com.patikafinal.graduationproject.repository.models.Role;
import com.patikafinal.graduationproject.repository.models.enums.ROLE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    AccountServiceImpl accountService;

    @Mock
    AccountDto accountDto;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(
                accountDto,
                passwordEncoder
        );
    }

    @Test
    void should_create_member() {
//        Mock
        Account account = new Account();
        account.setFirstName("Test Name");
        account.setId(1L);
        account.setPassword("payten&patika");
        account.setMail("alicanli1995@gmail.com");
        account.setLastName("Test LastName");
        account.setRoles(Collections.singleton
                ((Role.builder().userRole(ROLE.USER).build().convertToRoleEntity())));

        when(accountDto.saveMember(ArgumentMatchers.any(AccountEntity.class))).thenReturn(account);
//         When
        Account created = accountService.saveMember(account);
//         Then
        assertThat(created.getFirstName()).isSameAs(account.getFirstName());
        assertThat(created.getLastName()).isSameAs(account.getLastName());
        assertThat(created.getMail()).isSameAs(account.getMail());
        assertThat(created.getRoles()).isSameAs(account.getRoles());
        assertThat(created.getPassword()).isSameAs(account.getPassword());
        verifyNoMoreInteractions(accountDto);
    }

    @Test
    void retrieveByMail() {
//        Mock
        AccountEntity account = new AccountEntity();
        account.setFirstName("Test Name");
        account.setId(1L);
        account.setPassword("payten&patika");
        account.setMail("alicanli1995@gmail.com");
        account.setLastName("Test LastName");
        account.setRoles(Collections.singleton
                ((Role.builder().userRole(ROLE.USER).build().convertToRoleEntity())));

        when(accountDto.findByMail("alicanli1995@gmail.com")).thenReturn(account);
//         When
        AccountEntity created = accountService.retrieveByMail("alicanli1995@gmail.com");
//         Then
        assertThat(created.getFirstName()).isSameAs(account.getFirstName());
        assertThat(created.getLastName()).isSameAs(account.getLastName());
        assertThat(created.getMail()).isSameAs(account.getMail());
        assertThat(created.getRoles()).isSameAs(account.getRoles());
        assertThat(created.getPassword()).isSameAs(account.getPassword());
        verify(accountDto).findByMail("alicanli1995@gmail.com");
        verifyNoMoreInteractions(accountDto);
    }

}