package com.glady.challenge.repository;

import com.glady.challenge.model.company.Company;

import java.math.BigDecimal;
import java.util.Optional;

public class CompanyRepository {

    public Optional<Company> getCompanyById(int companyId) {
        return null;
    }

    public boolean reduceBalance(int companyId, BigDecimal reduceAmount) {
        return true;
    }

    public boolean addToBalance(int companyId, BigDecimal addAmount) {
        return true;
    }

}
