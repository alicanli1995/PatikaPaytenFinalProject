package com.patikafinal.graduationproject.controller;

import com.patikafinal.graduationproject.controller.request.MemberCreateRequest;
import com.patikafinal.graduationproject.controller.request.MemberEditRequest;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value="Member Controller API Documents")
public class MemberController {

    @ApiModelProperty(value = "We are dependency injection User Services.")
    private final MemberService memberService;


    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The method for get all members for member page.")
    public ResponseEntity<List<Member>> member(){
        return ResponseEntity.ok(memberService.findAll());
    }


    @PostMapping("/memberAdd")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "The method for add members.")
    public Long add(@RequestBody MemberCreateRequest memberCreateRequest) {
        Member member = memberCreateRequest.convertToMember();
        if(memberService.contains(member.getTcNo())){

            return 0L;
        }
        else{
            return memberService.save(member).getId();
        }
    }

    @PostMapping("/memberEditSave/{id}")
    @ApiOperation(value = "The method for edited members save.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Member> todoEditSave(@PathVariable Long id , @RequestBody MemberEditRequest memberEdit) {
        return ResponseEntity.ok(memberService.update(id,memberEdit));
    }


    @DeleteMapping("/memberDelete/{id}")
    @ApiOperation(value = "The method for remove members.")
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }


}
