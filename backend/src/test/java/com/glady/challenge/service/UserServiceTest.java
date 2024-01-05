package com.glady.challenge.service;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void init(){
        userService = new UserService(userRepository);
    }

    @Test
    void given_a_user_with_only_gifts_it_should_return_a_balance(){

        GiftBenefit giftBenefit1 = new GiftBenefit();
        giftBenefit1.setAmount(new BigDecimal(100));
        giftBenefit1.setEmittedOn(LocalDate.now().minusDays(1));

        GiftBenefit giftBenefit2 = new GiftBenefit();
        giftBenefit2.setAmount(new BigDecimal(200));
        giftBenefit2.setEmittedOn(LocalDate.now().minusDays(1));

        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1,giftBenefit2));

        User user = new User();
        user.setGiftBenefits(userGiftBenefits);
        userRepository.save(user);


        Assertions.assertEquals(new BigDecimal(300), userService.getUserBenefitBalance(1l));
    }

    @Test
    void given_a_user_with_only_meals_it_should_return_a_balance(){
        MealBenefit mealBenefit1 = new MealBenefit();
        mealBenefit1.setAmount(new BigDecimal(300));
        mealBenefit1.setEmittedOn(LocalDate.now().minusDays(1));


        MealBenefit mealBenefit2 = new MealBenefit();
        mealBenefit2.setAmount(new BigDecimal(400));
        mealBenefit2.setEmittedOn(LocalDate.now().minusDays(1));

        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1,mealBenefit2));

        User user = new User();
        user.setMealBenefits(userMealBenefits);
        userRepository.save(user);

        Assertions.assertEquals(new BigDecimal(700), userService.getUserBenefitBalance(1l));
    }

    @Test
    void given_a_user_with_gifts_and_meals_benefits_should_return_sum_of_both_benefits(){
        GiftBenefit giftBenefit1 = new GiftBenefit();
        giftBenefit1.setAmount(new BigDecimal(100));
        giftBenefit1.setEmittedOn(LocalDate.now().minusDays(1));
        GiftBenefit giftBenefit2 = new GiftBenefit();
        giftBenefit2.setAmount(new BigDecimal(200));
        giftBenefit2.setEmittedOn(LocalDate.now().minusDays(1));

        MealBenefit mealBenefit1 = new MealBenefit();
        mealBenefit1.setAmount(new BigDecimal(300));
        mealBenefit1.setEmittedOn(LocalDate.now().minusDays(1));
        MealBenefit mealBenefit2 = new MealBenefit();
        mealBenefit2.setAmount(new BigDecimal(400));
        mealBenefit2.setEmittedOn(LocalDate.now().minusDays(1));


        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1,giftBenefit2));
        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1,mealBenefit2));

        User user = new User();
        user.setMealBenefits(userMealBenefits);
        user.setGiftBenefits(userGiftBenefits);
        userRepository.save(user);
        System.out.println(userRepository.findAll());

        Assertions.assertEquals(new BigDecimal(1000), userService.getUserBenefitBalance(1l));
    }
}
