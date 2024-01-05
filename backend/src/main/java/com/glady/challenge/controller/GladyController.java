package com.glady.challenge.controller;

import com.glady.challenge.model.benefit.dto.GiftBenefitDto;
import com.glady.challenge.model.benefit.dto.MealBenefitDto;
import com.glady.challenge.service.CompanyService;
import com.glady.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/glady/api/")
public class GladyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @PostMapping("/benefits/gift")
    public ResponseEntity<?> createGiftDeposit(@RequestBody GiftBenefitDto giftBenefitDto) {
        try {
            GiftBenefitDto giftBenefitDtoResult = companyService.distributeGiftBenefit(
                    giftBenefitDto.getCompanyId(),
                    giftBenefitDto.getUserId(),
                    giftBenefitDto.getAmount());

            return new ResponseEntity<>(giftBenefitDtoResult, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error on gift benefit creation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/benefits/meal")
    public ResponseEntity<?> createMealDeposit(@RequestBody MealBenefitDto mealBenefitDto) {
        try {
            MealBenefitDto mealBenefitDtoResult = companyService.distributeMealBenefit(
                    mealBenefitDto.getCompanyId(),
                    mealBenefitDto.getUserId(),
                    mealBenefitDto.getAmount());

            return new ResponseEntity<>(mealBenefitDtoResult, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error on meal benefit creation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/balance/{userId}")
    public ResponseEntity<?> getUserBalance(@PathVariable Long userId) {
        try {
            BigDecimal balance = userService.getUserBenefitBalance(userId);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return new ResponseEntity<>("Error on meal benefit creation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}