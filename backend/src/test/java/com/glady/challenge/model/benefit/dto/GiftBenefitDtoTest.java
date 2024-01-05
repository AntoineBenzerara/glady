package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GiftBenefitDtoTest {

    @Test
    void given_an_entity_gift_benefit_it_should_produce_a_benefit_dto() {
        GiftBenefit gift = new GiftBenefit();
        gift.setId(Long.valueOf(123l));
        gift.setAmount(BigDecimal.TEN);
        User user = new User();
        user.setId(1l);
        gift.setUser(user);
        LocalDate now = LocalDate.now();
        gift.setEmittedOn(now);
        Company company = Mockito.mock(Company.class);
        company.setId(1l);
        gift.setCompany(company);


        GiftBenefitDto giftBenefitDto = GiftBenefitDto.ofGiftBenefit(gift);
        assertEquals(gift.getId(), giftBenefitDto.getId());
        assertEquals(gift.getAmount(), giftBenefitDto.getAmount());
        assertEquals(gift.getEmittedOn(), giftBenefitDto.getEmittedOn());
        assertEquals(gift.getUser().getId(), giftBenefitDto.getUserId());
        assertEquals(gift.getCompany().getId(), giftBenefitDto.getCompanyId());
    }
}
