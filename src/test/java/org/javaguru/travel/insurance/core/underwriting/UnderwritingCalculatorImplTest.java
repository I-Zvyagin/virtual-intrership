package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnderwritingCalculatorImplTest {

    @Mock
    private TravelRiskPremiumCalculator travelRiskPremiumCalculatorMedical;

    @Mock
    private TravelRiskPremiumCalculator travelRiskPremiumCalculatorCancellation;

    private UnderwritingCalculatorImpl underwritingCalculatorImpl;

    @Mock
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void init() {
        underwritingCalculatorImpl = new UnderwritingCalculatorImpl(List.of(travelRiskPremiumCalculatorMedical,
                travelRiskPremiumCalculatorCancellation));
    }

    @Test
    void calculateAgreementPrice() {
        when(travelRiskPremiumCalculatorMedical.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculatorCancellation.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(travelRiskPremiumCalculatorMedical.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(travelRiskPremiumCalculatorCancellation.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        TravelPremiumCalculationResult premiumCalculationResult = underwritingCalculatorImpl.calculatePremium(request);
        assertEquals(new BigDecimal(2), premiumCalculationResult.totalPremium());
    }
}