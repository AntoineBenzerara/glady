package com.glady.challenge.repository;

import com.glady.challenge.model.benefit.Benefit;
import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Mock repository class for User
@Repository
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

}
