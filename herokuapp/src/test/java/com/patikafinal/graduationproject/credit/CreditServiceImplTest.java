package com.patikafinal.graduationproject.credit;

import com.patikafinal.graduationproject.repository.credit.CreditDto;
import com.patikafinal.graduationproject.repository.credit.CreditEntity;
import com.patikafinal.graduationproject.repository.models.Member;
import com.patikafinal.graduationproject.service.credit.CreditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    CreditServiceImpl creditService;

    @Mock
    CreditDto creditDto;

    @BeforeEach
    void setUp() {
        creditService = new CreditServiceImpl(
                creditDto
        ) {
        };
    }


    @Test
    void should_getCreditLimit_on_member() {
//        Mock
        Double mockVal = 4000.0;
        Boolean mockBool = true;
        Member mockMember = Member.builder()
                .id(1L)
                .creditScore(3000)
                .phoneNumber("05375123006")
                .name("Ali")
                .lastName("CANLI")
                .tcNo("12345678911")
                .salary(1000L)
                .build();

        when(creditDto.getCredit(mockVal,mockBool,mockMember)).thenReturn(4000.0);

        //        When
        Double returnedLimit = creditService.getCreditLimit(mockMember);

        //        Then
        assertThat(returnedLimit.equals(4000.0));

        verifyNoMoreInteractions(creditDto);
    }

    @Test
    void should_retrieve_with_id_return_creditEntity() {
        //        Mock
        CreditEntity mockCredit = new CreditEntity();
        mockCredit.setId(1L);
        mockCredit.setTcNo("98765432145");
        mockCredit.setCreditStatus(true);
        mockCredit.setCreditLimit(10000.0);
        mockCredit.setCreatedDate(LocalDateTime.now());

        when(creditDto.retrieve(1L)).thenReturn(mockCredit);
        //        When
        CreditEntity creditEntity = creditService.retrieve(1L);

        //        Then
        assertThat(creditEntity).isNotNull();
        assertThat(creditEntity.getId()).isEqualTo(1L);
        assertThat(creditEntity.getCreditLimit()).isEqualTo(mockCredit.getCreditLimit());
        assertThat(creditEntity.getCreatedDate()).isEqualTo(mockCredit.getCreatedDate());
        assertThat(creditEntity.getTcNo()).isEqualTo(mockCredit.getTcNo());
        assertThat(creditEntity.getCreditStatus()).isEqualTo(mockCredit.getCreditStatus());

        verifyNoMoreInteractions(creditDto);

    }
}