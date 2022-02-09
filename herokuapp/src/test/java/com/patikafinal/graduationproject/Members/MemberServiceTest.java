package com.patikafinal.graduationproject.Members;

import com.patikafinal.graduationproject.repository.credit.CreditDto;
import com.patikafinal.graduationproject.repository.member.MemberDto;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    MemberServiceImpl memberService;

    @Mock
    MemberDto memberDto;

    @Mock
    CreditDto creditDto;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(
                memberDto,
                creditDto
        ) {
        };
    }

    @Test
    void should_create_member() {
//        Mock
        Member member = new Member();
        member.setId(3L);
        member.setCreditScore(200);
        member.setSalary(9999L);
        member.setTcNo("12345678998");

        when(memberDto.save(ArgumentMatchers.any(MemberEntity.class))).thenReturn(member);

//        When
        Member returnedMember = memberService.save(member);

//        Then
        assertThat(returnedMember.getTcNo()).isSameAs(member.getTcNo());
        assertThat(returnedMember.getId()).isSameAs(member.getId());
        assertThat(returnedMember.getSalary()).isSameAs(member.getSalary());

        verifyNoMoreInteractions(memberDto);

    }

    @Test
    void should_retrieve_with_id_return_member(){
        //        Mock
        Member mockMember = Member.builder()
                .id(1L)
                .creditScore(3000)
                .phoneNumber("05375123006")
                .name("Ali")
                .lastName("CANLI")
                .tcNo("12345678911")
                .salary(9999L)
                .build();

        when(memberDto.retrieveMember(1L)).thenReturn(mockMember);
        //        When
        Member member = memberService.retrieve(1L);

        //        Then
        assertThat(member).isNotNull();
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getCreditScore()).isEqualTo(3000);
        assertThat(member.getSalary()).isEqualTo(9999L);
        assertThat(member.getPhoneNumber()).isEqualTo("05375123006");
        assertThat(member.getTcNo()).isEqualTo("12345678911");

        verifyNoMoreInteractions(memberDto);
    }

    @Test
    void should_retrieve_with_tcNo_return_member(){
        //        Mock
        Member mockMember = Member.builder()
                .id(1L)
                .creditScore(3000)
                .phoneNumber("05375123006")
                .name("Ali")
                .lastName("CANLI")
                .tcNo("12345678911")
                .salary(9999L)
                .build();

        when(memberDto.retrieveByTcNo("12345678911")).thenReturn(mockMember);
        //        When
        Member member = memberService.retrieveByTcNo("12345678911");

        //        Then
        assertThat(member).isNotNull();
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getCreditScore()).isEqualTo(3000);
        assertThat(member.getSalary()).isEqualTo(9999L);
        assertThat(member.getPhoneNumber()).isEqualTo("05375123006");
        assertThat(member.getTcNo()).isEqualTo("12345678911");

        verifyNoMoreInteractions(memberDto);
    }






}