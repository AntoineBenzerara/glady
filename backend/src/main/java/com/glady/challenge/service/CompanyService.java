package com.glady.challenge.service;

import com.glady.challenge.model.benefit.Benefit;
import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.benefit.dto.GiftBenefitDto;
import com.glady.challenge.model.benefit.dto.MealBenefitDto;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.CompanyRepository;
import com.glady.challenge.repository.GiftBenefitRepository;
import com.glady.challenge.repository.MealBenefitRepository;
import com.glady.challenge.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    private final GiftBenefitRepository giftBenefitRepository;

    private final MealBenefitRepository mealBenefitRepository;

    public CompanyService(@Autowired CompanyRepository companyRepository, @Autowired UserRepository userRepository, @Autowired GiftBenefitRepository giftBenefitRepository, @Autowired MealBenefitRepository mealBenefitRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.mealBenefitRepository = mealBenefitRepository;
        this.giftBenefitRepository = giftBenefitRepository;
    }

    public MealBenefitDto distributeMealBenefit(Long companyId, Long userId, BigDecimal amount){
        return MealBenefitDto.ofMealBenefit((MealBenefit) distributeBenefit(companyId,userId,amount, MealBenefit.class));
    }

    public GiftBenefitDto distributeGiftBenefit(Long companyId, Long userId, BigDecimal amount){
        return GiftBenefitDto.ofGiftBenefit((GiftBenefit) distributeBenefit(companyId,userId,amount, GiftBenefit.class));
    }


    @Transactional
    public Benefit distributeBenefit(Long companyId, Long userId, BigDecimal amount, Class benefitClass ) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Amount should be > 0");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found with that id"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("No company found with that id"));

        if (!company.hasEnoughBalance(amount)) {
            throw new RuntimeException("Company balance is not enough.");
        }


        //Instantiating new benefit depending on which class type it is
        Benefit benefit;
        JpaRepository benefitRepository;
        if( benefitClass == MealBenefit.class) {
            benefit = new MealBenefit();
            benefitRepository = mealBenefitRepository;
        } else if (benefitClass == GiftBenefit.class) {
            benefit = new GiftBenefit();
            benefitRepository = giftBenefitRepository;
        }else {
            throw new RuntimeException("This benefit is not yet supported");
        }

        benefit.setAmount(amount);
        benefit.setEmittedOn(LocalDate.now());
        benefit.setUser(user);

        //Reducing balance for company
        company.setBalance(company.getBalance().subtract(amount));
        companyRepository.saveAndFlush(company);

        //Casting to generic benefit. This is only possible as MealBenefit and GiftBenefit have the same parameters
        return (Benefit) benefitRepository.save(benefit);

    }



}
