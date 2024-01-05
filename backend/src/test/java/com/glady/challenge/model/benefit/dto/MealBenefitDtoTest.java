package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MealBenefitDtoTest {


    @Test
    void given_an_entity_meal_benefit_it_should_produce_a_benefit_dto() {
        MealBenefit meal = new MealBenefit();
        meal.setId(Long.valueOf(123l));
        meal.setAmount(BigDecimal.TEN);
        User user = new User();
        user.setId(1l);
        meal.setUser(user);
        LocalDate now = LocalDate.now();
        meal.setEmittedOn(now);
        Company company = Mockito.mock(Company.class);
        company.setId(1l);
        meal.setCompany(company);

        MealBenefitDto mealBenefitDto = MealBenefitDto.ofMealBenefit(meal);
        assertEquals(meal.getId(), mealBenefitDto.getId());
        assertEquals(meal.getAmount(), mealBenefitDto.getAmount());
        assertEquals(meal.getEmittedOn(), mealBenefitDto.getEmittedOn());
        assertEquals(meal.getUser().getId(), mealBenefitDto.getUserId());
        assertEquals(meal.getCompany().getId(), mealBenefitDto.getCompanyId());
    }
}
