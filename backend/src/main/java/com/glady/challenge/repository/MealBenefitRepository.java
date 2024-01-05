package com.glady.challenge.repository;

import com.glady.challenge.model.benefit.MealBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealBenefitRepository extends JpaRepository<MealBenefit, Long> {
}
