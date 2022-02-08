package com.patikafinal.graduationproject.repository.models;

import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import lombok.*;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private Long id;

    private String tcNo;

    private Double creditLimit;

    private String created;

    private String completed;


    private Boolean creditStatus;

    public static Credit convertFromEntity(CreditEntity creditEntity)
    {
        return Credit.builder()
                .id(creditEntity.getId())
                .tcNo(creditEntity.getTcNo())
                .completed(creditEntity.getCompleted())
                .creditLimit(creditEntity.getCreditLimit())
                .creditStatus(creditEntity.getCreditStatus())
                .created(creditEntity.getCreatedDate().toString())
                .build();
    }

    public CreditEntity convertToCreditEntity() {
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setCompleted(completed);
        creditEntity.setCreditLimit(creditLimit);
        creditEntity.setCreditStatus(creditStatus);
        creditEntity.setTcNo(tcNo);
        creditEntity.setId(id);
        return creditEntity;
    }
}
