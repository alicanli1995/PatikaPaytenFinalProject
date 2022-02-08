package com.patikafinal.graduationproject.IntegrationTests.Credit;


import com.patikafinal.graduationproject.IntegrationTests.BaseIntegrationTest;
import com.patikafinal.graduationproject.controller.request.GetCreditRequest;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.credit.CreditJpaDto;
import com.patikafinal.graduationproject.repository.models.Credit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreditQueryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    CreditJpaDto creditJpaDto;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/create-member.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/create-credit.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_get_credit_in_exist_user_in_database() {
        //given
        GetCreditRequest getCreditRequest = new GetCreditRequest();
        getCreditRequest.setTcNo("98765432145");

        //when
        ResponseEntity<Credit> response =
                testRestTemplate.postForEntity("/status/get", getCreditRequest, Credit.class);

        //when
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCompleted()).isEqualTo("YES");
        Optional<CreditEntity> creditEntity = creditJpaDto.findByTcNo("98765432145");
        assertThat(creditEntity).isPresent();
        assertThat(creditEntity.get()).extracting("creditStatus", "creditLimit","completed")
                .containsExactly(Boolean.TRUE,25000.0,"YES");
    }



}
