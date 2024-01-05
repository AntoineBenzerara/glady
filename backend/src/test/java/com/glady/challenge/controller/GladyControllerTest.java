package com.glady.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glady.challenge.model.benefit.GiftBenefit;
import com.glady.challenge.model.benefit.dto.GiftBenefitDto;
import com.glady.challenge.model.benefit.dto.MealBenefitDto;
import com.glady.challenge.service.CompanyService;
import com.glady.challenge.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GladyController.class)
public class GladyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private UserService userService;

    // Write tests for each endpoint
    @Test
    public void createGiftBenefitTest() throws Exception {
        // Set up your DTO

        GiftBenefitDto giftDto = GiftBenefitDto.builder()
                .userId(1l)
                .companyId(1l)
                .amount(BigDecimal.valueOf(100))
                .build();

        // Set up the service mock
        given(companyService.distributeGiftBenefit(anyLong(), anyLong(), any(BigDecimal.class))).willReturn(giftDto);

        // Perform the request and assert the response
        mockMvc.perform(post("/glady/api/benefits/gift")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(giftDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createMealBenefitTest() throws Exception {
        // Set up your DTO

        MealBenefitDto mealDto = MealBenefitDto.builder()
                .userId(1l)
                .companyId(1l)
                .amount(BigDecimal.valueOf(100))
                .build();

        // Set up the service mock
        given(companyService.distributeMealBenefit(anyLong(), anyLong(), any(BigDecimal.class))).willReturn(mealDto);

        // Perform the request and assert the response
        mockMvc.perform(post("/glady/api/benefits/gift")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mealDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getUserBalanceTest() throws Exception {

        // Set up the service mock
        given(userService.getUserBenefitBalance(anyLong())).willReturn(new BigDecimal("200"));

        // Perform the request and assert the response
        mockMvc.perform(get("/glady/api/users/balance/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("200"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}