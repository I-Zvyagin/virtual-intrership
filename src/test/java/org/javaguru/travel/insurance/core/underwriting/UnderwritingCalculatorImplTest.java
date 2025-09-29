package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.underwriting.UnderwritingCalculator;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
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
class UnderwritingCalculatorImplTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @InjectMocks
    private UnderwritingCalculatorImpl underwritingCalculatorImpl;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void calculateAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(126,7,25));
        when(request.getAgreementDateTo()).thenReturn(new Date(126,7,29));
        when(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .thenReturn(4L);
        BigDecimal agreementPrice = underwritingCalculatorImpl.calculateAgreementPrice(request);
        assertEquals(4L, agreementPrice.longValue());
    }
}