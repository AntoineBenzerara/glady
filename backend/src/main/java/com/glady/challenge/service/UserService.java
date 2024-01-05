package com.glady.challenge.service;

import com.glady.challenge.model.benefit.Benefit;
import com.glady.challenge.model.user.User;
import com.glady.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BigDecimal getMealBenefitBalance(User user, LocalDate date) {
        return user.getMealBenefits().stream()
                .filter(mb -> mb.expireOn().isAfter(date))
                .map(Benefit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getGiftBenefitBalance(User user, LocalDate date) {
        return user.getGiftBenefits().stream()
                .filter(mb -> mb.expireOn().isAfter(date))
                .map(Benefit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Return current balance of both meal and gift benefits. Only gifts that are valid are summed
     * @param userId
     * @return current user balance
     */
    public BigDecimal getUserBenefitBalance(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        //Assuming JVM LocalDate is on same TZ as repository where are stored benefits
        LocalDate now = LocalDate.now();

        return getGiftBenefitBalance(user, now)
                .add(getMealBenefitBalance(user, now));
    }
}
