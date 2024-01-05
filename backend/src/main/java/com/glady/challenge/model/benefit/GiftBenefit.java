package com.glady.challenge.model.benefit;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class GiftBenefit extends Benefit {

    public LocalDate expireOn() {
        // Not 365 days but day before 1 year anniversary to manage odd years
        // Otherwise code would be getEmittedAt().plusDays(365);
        return this.getEmittedOn().plusYears(1).minusDays(1);
    }
}
