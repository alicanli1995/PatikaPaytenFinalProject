package com.patikafinal.graduationproject.IntegrationTests.Members;

import com.patikafinal.graduationproject.IntegrationTests.BaseIntegrationTest;
import com.patikafinal.graduationproject.controller.request.MemberEditRequest;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.member.MemberJpaDto;
import com.patikafinal.graduationproject.repository.models.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UpdateMemberIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MemberJpaDto memberJpaDto;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/create-member.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_delete_member() {
        //given
        Optional<MemberEntity> optionalMember = memberJpaDto.findById(1L);
        assertThat(optionalMember).isPresent();

        MemberEditRequest memberEditRequest = new MemberEditRequest();
        memberEditRequest.setLastName("Edited Last Name");
        memberEditRequest.setName("Edited Name");
        memberEditRequest.setSalary(2222L);
        memberEditRequest.setPhoneNumber("33333333333");

        //when
        ResponseEntity<Member> memberResponseEntity =
                testRestTemplate.postForEntity("/memberEditSave/1",memberEditRequest,Member.class);

        //then
        assertThat(memberResponseEntity).isNotNull();
        assertThat(Objects.requireNonNull(memberResponseEntity.getBody()).getSalary()).isEqualTo(2222L);
        Optional<MemberEntity> memberEntity = memberJpaDto.findById(memberResponseEntity.getBody().getId());
        assertThat(memberEntity).isPresent();
        assertThat(memberEntity.get()).extracting("name","salary")
                .containsExactly("Edited Name",2222L);

    }

}
