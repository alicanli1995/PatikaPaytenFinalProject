package com.patikafinal.graduationproject.controller;

import com.patikafinal.graduationproject.controller.request.MemberCreateRequest;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Api(value="Member Controller API Documents")
public class MemberController {

    @ApiModelProperty(value = "We are dependency injection User Services.")
    private final MemberService memberService;


    @GetMapping("/members")
    @ApiOperation(value = "The method for get all members for member page.")
    public String member(Model model){
        model.addAttribute("member", memberService.findAll());
        return "members";
    }


    @GetMapping("/index")
    @ApiOperation(value = "The method for get all members for index page.")
    public String home(Model model)  {
        model.addAttribute("member", memberService.findAll());
        return "index";
    }


    @PostMapping("/memberAdd")
    @ApiOperation(value = "The method for add members.")
    public String add(@ModelAttribute @Valid MemberCreateRequest memberCreateRequest) {
        Member member = memberCreateRequest.convertToMember();
        if(memberService.contains(member.getTcNo())){
            return "redirect:/members?error";
        }
        else{
            memberService.save(member);
            return "redirect:/members?success";
        }
    }

    @PostMapping("/memberEditSave")
    @ApiOperation(value = "The method for edited members save.")
    public String todoEditSave( @ModelAttribute("member") MemberEntity memberEntity) {
        memberService.update(memberEntity);
        return "redirect:/members";
    }

    @RequestMapping("/showMemberEdit/{id}")
    @ApiOperation(value = "The method for editing members.")
    public String showFormForTodoUpdate(@PathVariable Long id ,Model theModel) {
        MemberEntity member = memberService.retrieve(id).convertToMemberEntity();
        theModel.addAttribute("member", member);
        return "memberedit";
    }

    @PostMapping("/memberDelete/{id}")
    @ApiOperation(value = "The method for remove members.")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/members";
    }


}
