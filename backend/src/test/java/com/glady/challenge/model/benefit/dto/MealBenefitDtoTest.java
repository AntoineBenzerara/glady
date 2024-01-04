package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MealBenefitDtoTest {


    @Test
    void given_an_entity_gift_benefit_it_should_produce_a_benefit_dto(){
        MealBenefit meal = new MealBenefit();
        meal.setId(Long.valueOf(123l));
        meal.setAmount(BigDecimal.TEN);
        User user = Mockito.mock(User.class);
        meal.setUser(user);
        LocalDate now = LocalDate.now();
        meal.setEmittedOn(now);

        MealBenefitDto mealBenefitDto = MealBenefitDto.ofMealBenefit(meal);
        Assertions.assertEquals(meal.getId(),mealBenefitDto.getId());
        Assertions.assertEquals(meal.getAmount(),mealBenefitDto.getAmount());
        Assertions.assertEquals(meal.getEmittedOn(),mealBenefitDto.getEmittedOn());
        Assertions.assertEquals(meal.getUser(),mealBenefitDto.getUser());
    }
}
