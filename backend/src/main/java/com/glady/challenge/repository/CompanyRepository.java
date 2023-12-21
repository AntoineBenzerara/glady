package com.glady.challenge.repository;

import com.glady.challenge.model.company.Company;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
//Mock repository class for Company
@Repository
public class CompanyRepository {

    public Optional<Company> getCompanyById(int companyId) {
        return null;
    }

    public boolean reduceBalance(int companyId, BigDecimal reduceAmount) {
        return true;
    }

}
