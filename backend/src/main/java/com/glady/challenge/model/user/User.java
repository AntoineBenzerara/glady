package com.glady.challenge.model.user;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;

import java.util.List;

public class User {

    private int id;

    private List<GiftBenefit> giftBenefits;

    private List<MealBenefit> mealBenefits;

    public User(int id, List<GiftBenefit> giftBenefits, List<MealBenefit> mealBenefits) {
        this.id = id;
        this.giftBenefits = giftBenefits;
        this.mealBenefits = mealBenefits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GiftBenefit> getGiftBenefits() {
        return giftBenefits;
    }

    public void setGiftBenefits(List<GiftBenefit> giftBenefits) {
        this.giftBenefits = giftBenefits;
    }

    public List<MealBenefit> getMealBenefits() {
        return mealBenefits;
    }

    public void setMealBenefits(List<MealBenefit> mealBenefits) {
        this.mealBenefits = mealBenefits;
    }
}
