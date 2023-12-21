package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class MealBenefit extends Benefit{

    public MealBenefit(BigDecimal amount, LocalDate emittedAt) {
        super(amount, emittedAt);
    }

    public LocalDate expireOn() {
        int expireOnYear = this.getEmittedAt().plusYears(1).getYear();
        int lastFebruaryDay = Year.isLeap(expireOnYear) ? 29 : 28;
        return LocalDate.of(expireOnYear, Month.FEBRUARY, lastFebruaryDay);
    }
}
