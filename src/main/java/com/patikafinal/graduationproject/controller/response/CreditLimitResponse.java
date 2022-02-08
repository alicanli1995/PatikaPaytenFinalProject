package com.patikafinal.graduationproject.controller.response;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreditLimitResponse {

    private Double creditLimit;
    private Boolean creditStatus;


    public static CreditLimitResponse convertToCreditResponse(Double creditLimit, Boolean creditStatus) {
        return CreditLimitResponse.builder()
                .creditLimit(creditLimit)
                .creditStatus(creditStatus)
                .build();
    }

}
