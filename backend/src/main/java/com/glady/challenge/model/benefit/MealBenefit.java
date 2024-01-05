package com.glady.challenge.model.benefit;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Entity
public class MealBenefit extends Benefit {

    public LocalDate expireOn() {
        int expireOnYear = this.getEmittedOn().plusYears(1).getYear();
        int lastFebruaryDay = Year.isLeap(expireOnYear) ? 29 : 28;
        return LocalDate.of(expireOnYear, Month.FEBRUARY, lastFebruaryDay);
    }
}
