package com.glady.challenge.service;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);

    UserService userService;

    List<GiftBenefit> userGiftBenefits;

    List<MealBenefit> userMealBenefits;
    @BeforeEach
    void init(){
        userService = new UserService(userRepository);
    }

    @Test
    void given_a_user_with_only_gifts_it_should_return_a_balance(){
        GiftBenefit giftBenefit2 = new GiftBenefit(new BigDecimal(200), LocalDate.now().minusDays(1));
        GiftBenefit giftBenefit1 = new GiftBenefit(new BigDecimal(100), LocalDate.now().minusDays(1));
        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1,giftBenefit2));
        Mockito.when(userRepository.findUserById(Mockito.anyInt())).thenReturn(Optional.of(new User(1,userGiftBenefits,new ArrayList<>())));

        Assertions.assertEquals(new BigDecimal(300), userService.getUserBenefitBalance(1));
    }

    @Test
    void given_a_user_with_only_meals_it_should_return_a_balance(){
        MealBenefit mealBenefit1  = new MealBenefit(new BigDecimal(300), LocalDate.now().minusDays(1));
        MealBenefit mealBenefit2 = new MealBenefit(new BigDecimal(400), LocalDate.now().minusDays(1));
        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1,mealBenefit2));
        Mockito.when(userRepository.findUserById(Mockito.anyInt())).thenReturn(Optional.of(new User(1,new ArrayList<>(),userMealBenefits)));

        Assertions.assertEquals(new BigDecimal(700), userService.getUserBenefitBalance(1));
    }

    @Test
    void given_a_user_with_gifts_and_meals_benefits_should_return_sum_of_both_benefits(){
        MealBenefit mealBenefit1  = new MealBenefit(new BigDecimal(300), LocalDate.now().minusDays(1));
        MealBenefit mealBenefit2 = new MealBenefit(new BigDecimal(400), LocalDate.now().minusDays(1));
        GiftBenefit giftBenefit2 = new GiftBenefit(new BigDecimal(200), LocalDate.now().minusDays(1));
        GiftBenefit giftBenefit1 = new GiftBenefit(new BigDecimal(100), LocalDate.now().minusDays(1));
        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1,giftBenefit2));
        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1,mealBenefit2));
        Mockito.when(userRepository.findUserById(Mockito.anyInt())).thenReturn(Optional.of(new User(1,userGiftBenefits,userMealBenefits)));

        Assertions.assertEquals(new BigDecimal(1000), userService.getUserBenefitBalance(1));
    }
}
