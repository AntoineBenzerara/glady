package com.glady.challenge.model.company.dto;

import com.glady.challenge.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Builder
@Getter
@AllArgsConstructor
public class CompanyDto {
    private final Long id;
    private final String name;
    private final BigDecimal balance;

    public static CompanyDto ofCompany(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .balance(company.getBalance())
                .name(company.getName())
                .build();
    }
}
