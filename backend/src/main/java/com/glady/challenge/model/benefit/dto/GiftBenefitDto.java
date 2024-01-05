package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.benefit.GiftBenefit;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftBenefitDto extends BenefitDto {

    @Builder
    public GiftBenefitDto(Long id, BigDecimal amount, LocalDate emittedAt, Long userId, LocalDate expireAt, Long companyId) {
        super(id, amount, emittedAt, userId, companyId, expireAt);
    }

    public static GiftBenefitDto ofGiftBenefit(GiftBenefit giftBenefit) {
        return GiftBenefitDto.builder()
                .id(giftBenefit.getId())
                .amount(giftBenefit.getAmount())
                .emittedAt(giftBenefit.getEmittedOn())
                .userId(giftBenefit.getUser().getId())
                .expireAt(giftBenefit.expireOn())
                .companyId(giftBenefit.getCompany().getId())
                .build();
    }

}
