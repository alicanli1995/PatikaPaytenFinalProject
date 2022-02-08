package com.patikafinal.graduationproject.IntegrationTests.Members;

import com.patikafinal.graduationproject.IntegrationTests.BaseIntegrationTest;
import com.patikafinal.graduationproject.repository.member.MemberEntity;
import com.patikafinal.graduationproject.repository.member.MemberJpaDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteMemberIntegrationTest extends BaseIntegrationTest {

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

        //when
        testRestTemplate.exchange("/memberDelete/1", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        //then
        optionalMember = memberJpaDto.findById(1L);
        assertThat(optionalMember).isEmpty();
    }

}
