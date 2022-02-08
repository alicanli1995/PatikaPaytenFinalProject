package com.patikafinal.graduationproject.IntegrationTests.Members;

import com.patikafinal.graduationproject.IntegrationTests.BaseIntegrationTest;
import com.patikafinal.graduationproject.controller.request.MemberCreateRequest;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.member.MemberJpaDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CreateMemberIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MemberJpaDto memberJpaDto;


    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_member() {
        //given
        MemberCreateRequest request = new MemberCreateRequest();
        request.setName("Test Member");
        request.setTcNo("12345678965");
        request.setSalary(9999L);
        request.setLastName("Test LastName");
        request.setPhoneNumber("05375123006");

        //when
        ResponseEntity<Long> response = testRestTemplate.postForEntity("/memberAdd", request, Long.class);


        //when
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(1L);

        Optional<MemberEntity> actorEntity = memberJpaDto.findById(response.getBody());
        assertThat(actorEntity).isPresent();
        assertThat(actorEntity.get()).extracting("name", "tcNo","salary")
                .containsExactly("Test Member","12345678965",9999L);
    }






}