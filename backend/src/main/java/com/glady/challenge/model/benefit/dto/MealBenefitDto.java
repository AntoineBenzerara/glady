package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class MealBenefitDto extends BenefitDto {

    @Builder
    public MealBenefitDto(Long id, BigDecimal amount, LocalDate emittedOn, User user, LocalDate expireOn) {
        super(id, amount, emittedOn, user, expireOn);
    }

    public static MealBenefitDto ofMealBenefit(MealBenefit mealBenefit) {
        return MealBenefitDto.builder()
                .id(mealBenefit.getId())
                .amount(mealBenefit.getAmount())
                .emittedOn(mealBenefit.getEmittedOn())
                .user(mealBenefit.getUser())
                .expireOn(mealBenefit.expireOn())
                .build();
    }
}
