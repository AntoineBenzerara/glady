package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.user.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefitDto extends BenefitDto{

    @Builder
    public GiftBenefitDto(Long id, BigDecimal amount, LocalDate emittedAt, User user, LocalDate expireAt) {
        super(id, amount, emittedAt, user, expireAt);
    }

    public static GiftBenefitDto ofGiftBenefit(GiftBenefit giftBenefit){
        return GiftBenefitDto.builder()
                .id(giftBenefit.getId())
                .amount(giftBenefit.getAmount())
                .emittedAt(giftBenefit.getEmittedOn())
                .user(giftBenefit.getUser())
                .expireAt(giftBenefit.expireOn())
                .build();
    }

}
