package com.patikafinal.graduationproject.repository.account;


import com.patikafinal.graduationproject.repository.models.BaseEntity;
import com.patikafinal.graduationproject.repository.role.RoleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter

@Entity(name = "account")
@Table(name = "account",uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
@EntityListeners(AuditingEntityListener.class)

public class AccountEntity extends BaseEntity {

    private String firstName;

    private String lastName;

    private String mail;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(
                    name = "account_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "role_id",referencedColumnName = "id"))
    private Collection<RoleEntity> roles;

}
