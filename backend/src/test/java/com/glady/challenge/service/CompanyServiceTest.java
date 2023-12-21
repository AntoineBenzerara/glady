package com.glady.challenge.service;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.CompanyRepository;
import com.glady.challenge.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {


    private static User user;
    private CompanyRepository companyRepository = mock(CompanyRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);
    private CompanyService companyService;


    @BeforeAll
    static void init(){

        GiftBenefit giftBenefit1 = new GiftBenefit(new BigDecimal(100), LocalDate.now());
        GiftBenefit giftBenefit2 = new GiftBenefit(new BigDecimal(200), LocalDate.now());
        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1,giftBenefit2));
        MealBenefit mealBenefit1  = new MealBenefit(new BigDecimal(300), LocalDate.now());
        MealBenefit mealBenefit2 = new MealBenefit(new BigDecimal(400), LocalDate.now());
        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1,mealBenefit2));

        user = new User(1,userGiftBenefits,userMealBenefits);

    }

    @BeforeEach
    void reset(){
        //Reseting Service to manage issue with mockito verify
        companyService = new CompanyService(companyRepository,userRepository);
    }

    @Test
    void given_an_unknown_company_id_companyService_should_not_emit_a_gift_benefit() {
        Assertions.assertFalse(companyService.distributeGiftBenefit(1,1,new BigDecimal(100)));
    }

    @Test
    void given_an_unknown_user_companyService_should_not_emit_a_gift_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        Assertions.assertFalse(companyService.distributeGiftBenefit(1,1,new BigDecimal(100)));
    }

    @Test
    void given_a_benefit_with_an_amount_greater_than_company_balance_should_not_emit_a_gift_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        Assertions.assertFalse(companyService.distributeGiftBenefit(1,1,new BigDecimal(1001)));
    }

    @Test
    void upon_an_issue_on_balance_update_companyService_should_not_emit_a_gift_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        when(companyRepository.reduceBalance(anyInt(),any(BigDecimal.class))).thenReturn(false);
        Assertions.assertFalse(companyService.distributeGiftBenefit(1,1,new BigDecimal(888)));
    }
    @Test
    void given_an_amount_and_a_user_a_company_should_emit_a_gift_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        when(companyRepository.reduceBalance(anyInt(),any(BigDecimal.class))).thenReturn(true);
        Assertions.assertFalse(companyService.distributeGiftBenefit(1,1,new BigDecimal(100)));
        verify(companyRepository).reduceBalance(anyInt(),any(BigDecimal.class));
        verify(userRepository).addGiftBenefit(anyInt(),any(GiftBenefit.class));
    }

    @Test
    void given_an_unknown_company_id_companyService_should_not_emit_a_meal_benefit() {
        Assertions.assertFalse(companyService.distributeMealBenefit(1,1,new BigDecimal(100)));
    }

    @Test
    void given_an_unknown_user_companyService_should_not_emit_a_meal_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        Assertions.assertFalse(companyService.distributeMealBenefit(1,1,new BigDecimal(100)));
    }

    @Test
    void given_a_benefit_with_an_amount_greater_than_company_balance_should_not_emit_a_meal_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        Assertions.assertFalse(companyService.distributeMealBenefit(1,1,new BigDecimal(1001)));
    }

    @Test
    void upon_an_issue_on_balance_update_companyService_should_not_emit_a_meal_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        when(companyRepository.reduceBalance(anyInt(),any(BigDecimal.class))).thenReturn(false);
        Assertions.assertFalse(companyService.distributeMealBenefit(1,1,new BigDecimal(888)));
    }
    @Test
    void given_an_amount_and_a_user_a_company_should_emit_a_meal_benefit(){
        when(companyRepository.getCompanyById(anyInt())).thenReturn(Optional.of(new Company(1, "gladyTestCompany", new BigDecimal(1000))));
        when(userRepository.findUserById(anyInt())).thenReturn(Optional.of(user));
        when(companyRepository.reduceBalance(anyInt(),any(BigDecimal.class))).thenReturn(true);
        Assertions.assertFalse(companyService.distributeMealBenefit(1,1,new BigDecimal(100)));
        verify(companyRepository).reduceBalance(anyInt(),any(BigDecimal.class));
        verify(userRepository).addMealBenefit(anyInt(),any(MealBenefit.class));
    }
}
