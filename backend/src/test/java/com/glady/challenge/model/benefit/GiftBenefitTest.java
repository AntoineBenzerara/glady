package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefitTest {

    User user = Mockito.mock(User.class);

    @Test
    void giftDeposit_has_365_days_lifespan(){
        GiftBenefit giftBenefit = new GiftBenefit(user,new BigDecimal(1234), LocalDate.now());
        Assertions.assertTrue(giftBenefit.expireOn().isEqual(LocalDate.now().plusDays(365)));
    }
}
