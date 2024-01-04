package com.glady.challenge.model.user.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
@AllArgsConstructor
public class UserDto {

    private final Long id;
    private final List<GiftBenefit> giftBenefits;
    private final List<MealBenefit> mealBenefits;

    public static UserDto ofUser(User user){
        return UserDto.builder()
                .id(user.getId())
                .giftBenefits(user.getGiftBenefits())
                .mealBenefits(user.getMealBenefits())
                .build();
    }
}
