package com.glady.challenge.repository;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Mock repository class for Benefit
@Repository
public class BenefitRepository {

    public Optional<MealBenefit> getMealBenefitById(int id) {
        return null;
    }

    public Optional<GiftBenefit> getGiftBenefitById(int id) {
        return null;
    }
}
