package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefit extends Benefit {

    public GiftBenefit(BigDecimal amount, LocalDate emittedAt) {
        super(amount, emittedAt);
    }

    public LocalDate expireOn() {
        // Not 365 days but day before 1 year anniversary to manage odd years
        // Otherwise code would be getEmittedAt().plusDays(365);
        return this.getEmittedAt().plusYears(1).minusDays(1);
    }
}
