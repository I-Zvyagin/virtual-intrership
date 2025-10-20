package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UnderwritingCalculatorImpl implements  UnderwritingCalculator{

    private  final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    @Override
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request){
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc -> {
                    BigDecimal riskPremium = getTravelRiskPremiumPrice(riskIc, request);
                    return new RiskPremium(riskIc, riskPremium);
                })
                .toList();

        BigDecimal totalPremium = riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
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