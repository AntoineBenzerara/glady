package com.glady.challenge.repository;

import com.glady.challenge.model.benefit.Benefit;
import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.user.User;

import java.util.Optional;

public class UserRepository {

    public Optional<User> findUserById(int userId) {
        return null;
    }

    public boolean addMealBenefit(int UserId, Benefit mealBenefit) {
        return true;
    }

    public boolean addGiftBenefit(int UserId, GiftBenefit giftBenefit) {
        return true;
    }

    public Optional<User> findUserByIdAndCompanyId(int userId, int companyId) {
        return null;
    }
}
