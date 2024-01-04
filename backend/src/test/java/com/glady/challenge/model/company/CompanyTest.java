package com.glady.challenge.model.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CompanyTest {
    @Test
    void given_an_amount_balance_check_should_return_false_if_bigger_than_company_balance(){
        Company company = new Company();
        company.setBalance(new BigDecimal(1000));
        Assertions.assertFalse(company.hasEnoughBalance(new BigDecimal(1001)));
    }

    @Test
    void given_an_amount_balance_check_should_return_true_if_smaller_than_company_balance(){
        Company company = new Company();
        company.setBalance(BigDecimal.valueOf(1000));
        Assertions.assertTrue(company.hasEnoughBalance(new BigDecimal(500)));
    }
}
