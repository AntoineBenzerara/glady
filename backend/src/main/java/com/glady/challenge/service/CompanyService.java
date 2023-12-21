package com.glady.challenge.service;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.CompanyRepository;
import com.glady.challenge.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CompanyService {


    private final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(@Autowired CompanyRepository companyRepository, @Autowired UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public boolean distributeGiftBenefit(int companyId, int userId, BigDecimal amount){

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            //TODO: Handle with exception
            LOGGER.error("Amount cannot be negative: amount provided {} ", amount);
            return false;
        }

        Company company = companyRepository.getCompanyById(companyId).orElse(null);

        if (company == null) {
            //TODO: Handle with exception
            LOGGER.error("No company found with {} id ", companyId);
            return false;
        }

        User user = userRepository.findUserByIdAndCompanyId(userId, companyId).orElse(null);
        if (user == null) {
            //TODO: Handle with exception
            LOGGER.error("User is either unknow or not part of this company ", companyId);
            return false;
        }

        if (!company.hasEnoughBalance(amount)) {
            LOGGER.error("Company {} does not have enough found. Current balance : {} ", company.getName(), company.getBalance());
            return false;
        }

        GiftBenefit giftBenefit = new GiftBenefit(amount, LocalDate.now());
        //TODO: add both update in a transaction to manage rollback upon failure
        if (companyRepository.reduceBalance(companyId, amount)) {
            return userRepository.addGiftBenefit(userId, giftBenefit);
        } else {
            LOGGER.error("Failure upon reduce balance for company {} on amount {} ", companyId, amount);
            return false;
        }
    }




}
