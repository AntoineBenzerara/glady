package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class MealBenefit extends Benefit{

    public MealBenefit(User user, BigDecimal amount, LocalDate emittedAt) {
        super(user, amount, emittedAt);
    }

    public LocalDate expireOn() {
        int expireOnYear = this.getEmittedAt().plusYears(1).getYear();
        return LocalDate.of(expireOnYear, Month.FEBRUARY, 28);
    }
}
