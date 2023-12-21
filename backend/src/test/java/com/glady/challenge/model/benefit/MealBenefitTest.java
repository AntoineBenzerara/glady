package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class MealBenefitTest {

    User user = Mockito.mock(User.class);

    @Test
    void mealBenefit_expire_on_last_feb_day(){
        MealBenefit mealBenefit = new MealBenefit(user, new BigDecimal(1234), LocalDate.now());
        Assertions.assertTrue(mealBenefit.expireOn().isEqual(LocalDate.of(mealBenefit.expireOn().getYear(), Month.FEBRUARY, 28)));
    }
}
