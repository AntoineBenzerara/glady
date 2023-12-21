package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefit extends Benefit {

    public GiftBenefit(User user, BigDecimal amount, LocalDate emittedAt) {
        super(user, amount, emittedAt);
    }

    public LocalDate expireOn() {
        return this.getEmittedAt().plusDays(365);
    }
}
