package com.patikafinal.graduationproject.repository.models;


import com.patikafinal.graduationproject.repository.models.enums.ROLE;
import com.patikafinal.graduationproject.repository.role.RoleEntity;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;
    private ROLE userRole;

    public static Role convertFromEntity(RoleEntity roleEntity)
    {
        return Role.builder()
                .id(roleEntity.getId())
                .userRole(roleEntity.getRoleUser())
                .build();
    }

    public RoleEntity convertToRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleUser(getUserRole());
        return roleEntity;
    }
}
