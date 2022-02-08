package com.patikafinal.graduationproject.controller;

import com.patikafinal.graduationproject.controller.request.CreateCreditLimitRequest;
import com.patikafinal.graduationproject.controller.request.GetCreditRequest;
import com.patikafinal.graduationproject.controller.response.CreditLimitResponse;
import com.patikafinal.graduationproject.exception.MemberNotFoundException;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.credit.CreditService;
import com.patikafinal.graduationproject.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@Api(value="Credit Controller API Documents")
public class CreditController {

    @ApiModelProperty(value = "We are dependency injection Credit And Member Services.")
    private final CreditService creditService;
    private final MemberService memberService;


    @PostMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The method get query for member credit limit.")
    public CreditLimitResponse queryCreditLimit(@RequestBody CreateCreditLimitRequest createCreditLimitRequest) {
        try {
            Member member = memberService.retrieveByTcNo(createCreditLimitRequest.getTcNo());
            Credit credit = creditService.getCreditLimit(member);
            log.info(member.getTcNo() + " is query credit limit.");
            return CreditLimitResponse.builder().creditLimit(credit.getCreditLimit()).creditStatus(credit.getCreditStatus()).build();
        } catch (MemberNotFoundException e) {
            log.warn(e.getDetails());
            return CreditLimitResponse.builder().build();
        }
    }

    @PostMapping("/status/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "The method for active credit status active / deactivate.")
    public ResponseEntity<Credit> update(@RequestBody GetCreditRequest getCreditRequest) {
        CreditEntity creditEntity = creditService.retrieve(getCreditRequest.getTcNo());
        if(creditEntity.getCreditStatus().equals(Boolean.TRUE)){
            if ("YES".equals(creditEntity.getCompleted())) {
                creditEntity.setCompleted("NO");
            } else {
                log.warn(creditEntity.getTcNo() + " is received credit.");
                creditEntity.setCompleted("YES");
            }
        }
        return ResponseEntity.ok(creditService.update(creditEntity));
    }

}
