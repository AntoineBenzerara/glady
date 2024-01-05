package com.glady.challenge.model.company.dto;

import com.glady.challenge.model.company.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CompanyDtoTest {

    @Test
    void given_a_company_entity_it_should_return_a_dto_with_same_values() {
        Company company = new Company();
        company.setId(Long.valueOf(123l));
        company.setName("glady");
        company.setBalance(BigDecimal.TEN);

        CompanyDto companyDto = CompanyDto.ofCompany(company);
        Assertions.assertEquals(company.getId(), companyDto.getId());
        Assertions.assertEquals(company.getName(), companyDto.getName());
        Assertions.assertEquals(company.getBalance(), companyDto.getBalance());
    }
}
