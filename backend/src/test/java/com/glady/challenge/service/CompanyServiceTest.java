package com.glady.challenge.service;

import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.MealBenefit;
import com.glady.challenge.model.benefit.dto.GiftBenefitDto;
import com.glady.challenge.model.benefit.dto.MealBenefitDto;
import com.glady.challenge.model.company.Company;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.CompanyRepository;
import com.glady.challenge.repository.GiftBenefitRepository;
import com.glady.challenge.repository.MealBenefitRepository;
import com.glady.challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CompanyServiceTest {


    private static User user;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GiftBenefitRepository giftBenefitRepository;
    @Autowired
    private MealBenefitRepository mealBenefitRepository;
    private static CompanyService companyService;

    private LocalDate nowDate = LocalDate.now();


    @BeforeEach
    void init() {

        Company company = new Company();
        company.setBalance(BigDecimal.valueOf(100));
        companyRepository.save(company);

        GiftBenefit giftBenefit1 = new GiftBenefit();
        giftBenefit1.setAmount(BigDecimal.valueOf(100));
        giftBenefit1.setEmittedOn(nowDate);
        giftBenefit1.setCompany(company);

        GiftBenefit giftBenefit2 = new GiftBenefit();
        giftBenefit2.setAmount(BigDecimal.valueOf(200));
        giftBenefit2.setEmittedOn(nowDate);
        giftBenefit2.setCompany(company);

        List<GiftBenefit> userGiftBenefits = new ArrayList<>(Arrays.asList(giftBenefit1, giftBenefit2));
        MealBenefit mealBenefit1 = new MealBenefit();
        mealBenefit1.setAmount(BigDecimal.valueOf(300));
        mealBenefit1.setEmittedOn(nowDate);
        mealBenefit1.setCompany(company);

        MealBenefit mealBenefit2 = new MealBenefit();
        mealBenefit2.setAmount(BigDecimal.valueOf(400));
        mealBenefit2.setEmittedOn(nowDate);
        mealBenefit2.setCompany(company);
        List<MealBenefit> userMealBenefits = new ArrayList<>(Arrays.asList(mealBenefit1, mealBenefit2));

        user = new User();
        user.setGiftBenefits(userGiftBenefits);
        user.setMealBenefits(userMealBenefits);

        userRepository.save(user);
        companyService = new CompanyService(companyRepository, userRepository, giftBenefitRepository, mealBenefitRepository);
    }

    @Test
    void given_an_unknown_company_id_companyService_should_not_emit_a_gift_benefit() {
        assertThrows(NoSuchElementException.class, () -> companyService.distributeGiftBenefit(2l, 1l, new BigDecimal(100)));
    }

    @Test
    void given_an_unknown_user_companyService_should_not_emit_a_gift_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(1000));
        company.setName("gladyTestCompany");
        companyRepository.save(company);
        assertThrows(NoSuchElementException.class, () -> companyService.distributeGiftBenefit(1l, 999l, new BigDecimal(100)));
    }

    @Test
    void given_a_benefit_with_an_amount_greater_than_company_balance_should_not_emit_a_gift_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(100));
        company.setName("gladyTestCompany");
        companyRepository.save(company);
        assertThrows(RuntimeException.class, () -> companyService.distributeGiftBenefit(1l, 1l, new BigDecimal(1000)));
    }

    @Test
    void given_an_amount_and_a_user_a_company_should_emit_a_gift_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(123456));
        company.setName("gladyTestCompany");
        companyRepository.save(company);

        GiftBenefitDto giftBenefitDtoResult = companyService.distributeGiftBenefit(1l, 1l, new BigDecimal(12345));
        GiftBenefitDto giftBenefitDtoInsert = GiftBenefitDto.ofGiftBenefit(giftBenefitRepository.findById(3l).get());
        assertEquals(giftBenefitDtoResult.getAmount(), giftBenefitDtoInsert.getAmount());
        assertEquals(giftBenefitDtoResult.getExpireOn(), giftBenefitDtoInsert.getExpireOn());
        assertEquals(giftBenefitDtoResult.getUserId(), giftBenefitDtoInsert.getUserId());
        assertEquals(giftBenefitDtoResult.getCompanyId(), giftBenefitDtoInsert.getCompanyId());
        assertEquals(giftBenefitDtoResult.getEmittedOn(), giftBenefitDtoInsert.getEmittedOn());

    }

    @Test
    void given_an_unknown_company_id_companyService_should_not_emit_a_meal_benefit() {
        assertThrows(NoSuchElementException.class, () -> companyService.distributeMealBenefit(2l, 1l, new BigDecimal(100)));
    }

    @Test
    void given_an_unknown_user_companyService_should_not_emit_a_meal_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(1000));
        company.setName("gladyTestCompany");
        companyRepository.save(company);
        assertThrows(NoSuchElementException.class, () -> companyService.distributeMealBenefit(1l, 999l, new BigDecimal(100)));
    }

    @Test
    void given_a_benefit_with_an_amount_greater_than_company_balance_should_not_emit_a_meal_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(100));
        company.setName("gladyTestCompany");
        companyRepository.save(company);
        assertThrows(RuntimeException.class, () -> companyService.distributeMealBenefit(1l, 1l, new BigDecimal(1000)));
    }

    @Test
    void given_an_amount_and_a_user_a_company_should_emit_a_meal_benefit() {
        Company company = companyRepository.findById(1l).get();
        company.setBalance(new BigDecimal(123456));
        company.setName("gladyTestCompany");
        companyRepository.save(company);

        MealBenefitDto mealBenefitDtoResult = companyService.distributeMealBenefit(1l, 1l, new BigDecimal(12345));
        MealBenefitDto mealBenefitDtoInsert = MealBenefitDto.ofMealBenefit(mealBenefitRepository.findById(3l).get());
        assertEquals(mealBenefitDtoResult.getAmount(), mealBenefitDtoInsert.getAmount());
        assertEquals(mealBenefitDtoResult.getExpireOn(), mealBenefitDtoInsert.getExpireOn());
        assertEquals(mealBenefitDtoResult.getUserId(), mealBenefitDtoInsert.getUserId());
        assertEquals(mealBenefitDtoResult.getCompanyId(), mealBenefitDtoInsert.getCompanyId());
        assertEquals(mealBenefitDtoResult.getEmittedOn(), mealBenefitDtoInsert.getEmittedOn());
    }

}
