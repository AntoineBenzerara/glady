package com.glady.challenge.model.benefit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class MealBenefitTest {
    private LocalDate now = LocalDate.now();

    @Test
    void mealBenefit_expire_on_last_feb_day(){
        //non leap year
        LocalDate now2021 = now.withYear(2021);
        LocalDate endOfFebruary = LocalDate.of(2022, Month.FEBRUARY, 28 );
        MealBenefit mealBenefit = new MealBenefit();
        mealBenefit.setEmittedOn(now2021);
        Assertions.assertTrue(mealBenefit.expireOn().isEqual(endOfFebruary));
    }

    @Test
    void mealBenefit_expire_on_last_feb_day_on_leap_year(){
        //set to 2023 as 2024 is a leap year
        LocalDate now2023 = now.withYear(2023);
        LocalDate endOfFebruary = LocalDate.of(2024, Month.FEBRUARY, 29 );
        MealBenefit mealBenefit = new MealBenefit();
        mealBenefit.setEmittedOn(now2023);
        System.out.println(endOfFebruary);
        System.out.println(mealBenefit.expireOn());
        Assertions.assertTrue(mealBenefit.expireOn().isEqual(endOfFebruary)) ;
    }
}
