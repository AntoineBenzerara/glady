package com.glady.challenge.model.benefit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefitTest {



    private LocalDate now;

    @BeforeEach
    void before(){
        now = LocalDate.now();
    }
    @Test
    void giftDeposit_has_365_days_lifespan(){
        //non leap year
        now = now.withYear(2021);
        GiftBenefit giftBenefit = new GiftBenefit(new BigDecimal(1234), now);
        Assertions.assertTrue(giftBenefit.expireOn().compareTo(now.plusDays(365)) == -1) ;

    }

    @Test
    void giftDeposit_have_1Year_lifespan_even_in_leap_year(){
        //next year is leap year
        now = now.withYear(2023);
        GiftBenefit giftBenefit = new GiftBenefit(new BigDecimal(1234), now);
        Assertions.assertTrue(giftBenefit.expireOn().compareTo(now.plusYears(1)) == -1) ;
    }
}
