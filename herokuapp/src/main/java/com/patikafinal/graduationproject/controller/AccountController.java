package com.patikafinal.graduationproject.controller;


import com.patikafinal.graduationproject.controller.request.AccountRegisterRequest;
import com.patikafinal.graduationproject.repository.models.Account;
import com.patikafinal.graduationproject.service.account.AccountService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
@Api(value="Account Controller API Documents")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @ModelAttribute("account")
    public AccountRegisterRequest accountRegisterRequest(){
        return new AccountRegisterRequest();
    }

    @PostMapping
    public String registerAccount(@ModelAttribute("account") AccountRegisterRequest accountRegisterRequest){
        Account account = accountRegisterRequest.convertToMember();
        if(accountService.contains(account.getMail())){
            return "redirect:/registration?error";
        }
        else{
            accountService.saveMember(account);
            return "redirect:/registration?success";
        }
    }



}
