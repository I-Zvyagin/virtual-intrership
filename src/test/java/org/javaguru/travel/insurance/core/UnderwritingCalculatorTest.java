package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnderwritingCalculatorTest {

    @InjectMocks
    private UnderwritingCalculator underwritingCalculator;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void calculateAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,16));
        BigDecimal agreementPrice = underwritingCalculator.calculateAgreementPrice(request);
        assertEquals(1L, agreementPrice.longValue());
    }
}