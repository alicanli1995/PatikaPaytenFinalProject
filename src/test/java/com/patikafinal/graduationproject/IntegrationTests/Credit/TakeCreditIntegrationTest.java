package com.patikafinal.graduationproject.IntegrationTests.Credit;

import com.patikafinal.graduationproject.IntegrationTests.BaseIntegrationTest;
import com.patikafinal.graduationproject.controller.request.CreateCreditLimitRequest;
import com.patikafinal.graduationproject.controller.response.CreditLimitResponse;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.credit.CreditJpaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TakeCreditIntegrationTest extends BaseIntegrationTest {

    @Autowired
    CreditJpaDto creditJpaDto;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/create-member.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_query_credit_limit_in_exist_user_in_database() {
        //given
        CreateCreditLimitRequest request = new CreateCreditLimitRequest();
        request.setTcNo("95465478921");

        //when
        ResponseEntity<CreditLimitResponse> response =
                testRestTemplate.postForEntity("/status", request, CreditLimitResponse.class);

        //when
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCreditLimit()).isEqualTo(0.0);
        assertThat(response.getBody().getCreditStatus()).isEqualTo(Boolean.FALSE);

        Optional<CreditEntity> creditEntity = creditJpaDto.findById(1L);
        assertThat(creditEntity).isPresent();
        assertThat(creditEntity.get()).extracting("creditStatus", "creditLimit","completed")
                .containsExactly(Boolean.FALSE,0.0,"NO");
    }


}
