package com.glady.challenge.model.benefit.dto;

import com.glady.challenge.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public abstract class BenefitDto {

    private final Long id;
    private final BigDecimal amount;
    private final LocalDate emittedOn;
    private final Long userId;
    private final Long companyId;
    private final LocalDate expireOn;
}
