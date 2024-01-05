package com.glady.challenge.model.user.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class UserDtoTest {

    @Test
    void given_a_user_entity_it_should_return_a_dto_with_same_values() {
        User user = new User();
        user.setId(Long.valueOf(123l));
        List<GiftBenefit> gifts = Arrays.asList(Mockito.mock(GiftBenefit.class));
        List<MealBenefit> meals = Arrays.asList(Mockito.mock(MealBenefit.class));
        user.setGiftBenefits(gifts);
        user.setMealBenefits(meals);

        UserDto userDto = UserDto.ofUser(user);
        Assertions.assertEquals(user.getId(), userDto.getId());
        Assertions.assertEquals(user.getMealBenefits(), userDto.getMealBenefits());
        Assertions.assertEquals(user.getGiftBenefits(), userDto.getGiftBenefits());
    }
}
