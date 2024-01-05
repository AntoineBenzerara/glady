package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefitDtoTest {

    @Test
    void given_an_entity_gift_benefit_it_should_produce_a_benefit_dto() {
        GiftBenefit gift = new GiftBenefit();
        gift.setId(Long.valueOf(123l));
        gift.setAmount(BigDecimal.TEN);
        User user = Mockito.mock(User.class);
        gift.setUser(user);
        LocalDate now = LocalDate.now();
        gift.setEmittedOn(now);

        GiftBenefitDto giftBenefitDto = GiftBenefitDto.ofGiftBenefit(gift);
        Assertions.assertEquals(gift.getId(), giftBenefitDto.getId());
        Assertions.assertEquals(gift.getAmount(), giftBenefitDto.getAmount());
        Assertions.assertEquals(gift.getEmittedOn(), giftBenefitDto.getEmittedOn());
        Assertions.assertEquals(gift.getUser(), giftBenefitDto.getUser());
    }
}
