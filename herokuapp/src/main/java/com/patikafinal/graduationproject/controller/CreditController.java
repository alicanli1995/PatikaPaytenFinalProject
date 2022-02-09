package com.patikafinal.graduationproject.controller;

import com.patikafinal.graduationproject.controller.request.CreateCreditLimitRequest;
import com.patikafinal.graduationproject.exception.MemberNotFoundException;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.credit.CreditService;
import com.patikafinal.graduationproject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping
public class CreditController {

    private String validUserTcNo;

    private final CreditService creditService;
    private final MemberService memberService;

    @GetMapping("/search")
    public String home(Model model){
        model.addAttribute("search", creditService.findAll().stream()
                .filter(credit -> credit.getTcNo().equals(validUserTcNo))
                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .toList());
        validUserTcNo = "";
        return "search";
    }


    @PostMapping("/status")
    public String  queryCreditLimit(@ModelAttribute("credit") CreateCreditLimitRequest createCreditLimitRequest) {
        try {
            Member member = memberService.retrieveByTcNo(createCreditLimitRequest.getTcNo());
            creditService.getCreditLimit(member);
            validUserTcNo = createCreditLimitRequest.getTcNo();
            log.info(member.getTcNo() + " is approved credit.");
            return "redirect:/search?success";
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return "redirect:/search?failed";
        }
    }

    @PostMapping("/status/{id}")
    public String update(@PathVariable Long id) {
        CreditEntity creditEntity = creditService.retrieve(id);
        if ("YES".equals(creditEntity.getCompleted())) {
            creditEntity.setCompleted("NO");
        } else {
            creditEntity.setCompleted("YES");
        }
        validUserTcNo = creditEntity.getTcNo();
        creditService.update(creditEntity);
        return "redirect:/search";
    }

}
