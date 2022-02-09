package com.patikafinal.graduationproject.service.account;

import com.patikafinal.graduationproject.repository.account.AccountDto;
import com.patikafinal.graduationproject.repository.account.AccountEntity;
import com.patikafinal.graduationproject.repository.models.Account;
import com.patikafinal.graduationproject.repository.models.Role;
import com.patikafinal.graduationproject.repository.role.RoleEntity;
import com.patikafinal.graduationproject.repository.models.enums.ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountDto accountDto;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public Account saveMember(Account user) {
        AccountEntity accountEntity = user.convertToAccountEntity();
        accountEntity.setRoles(Collections.singleton
                ((Role.builder().userRole(ROLE.USER).build().convertToRoleEntity())));
        accountEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountDto.saveMember(accountEntity);
    }

    @Override
    public AccountEntity retrieveByMail(String mail) {
        return accountDto.findByMail(mail);
    }

    @Override
    public boolean contains(String mail) {
        return accountDto.contains(mail);
    }


    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountDto.findByMail(accountName);
        Collection<RoleEntity> role =  accountEntity.getRoles();
        Collection<Role> role1 = role.stream().map(Role::convertFromEntity).collect(Collectors.toList());
        if (accountName == null){
            throw new UsernameNotFoundException("Invalid username or pass.");
        }
        return new org.springframework.security.core.userdetails.User(
                accountEntity.getMail(),accountEntity.getPassword(),mapRolesToAuthorities(role1));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                .collect(Collectors.toList());
    }
}
