package com.glady.challenge.model.benefit;

import com.glady.challenge.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Benefit {

    private User user;
    private BigDecimal amount;
    private LocalDate emittedAt;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getEmittedAt() {
        return emittedAt;
    }

    public void setEmittedAt(LocalDate emittedAt) {
        this.emittedAt = emittedAt;
    }

    public Benefit(User user, BigDecimal amount, LocalDate emittedAt) {
        //Manage id generation at persistance level
        this.user = user;
        this.amount = amount;
        this.emittedAt = emittedAt;
    }

}
