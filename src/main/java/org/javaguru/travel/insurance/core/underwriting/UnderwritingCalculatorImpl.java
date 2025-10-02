package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UnderwritingCalculatorImpl implements  UnderwritingCalculator{

    private  final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return request.getSelectedRisks().stream()
                .map(risk -> getTravelRiskPremiumPrice(risk, request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTravelRiskPremiumPrice (String riskIc, TravelCalculatePremiumRequest request) {
        TravelRiskPremiumCalculator riskPremiumCalculator = getTravelRiskPremiumCalculators(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator getTravelRiskPremiumCalculators (String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}