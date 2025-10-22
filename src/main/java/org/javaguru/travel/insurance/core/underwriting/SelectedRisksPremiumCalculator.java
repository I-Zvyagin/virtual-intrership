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
public class SelectedRisksPremiumCalculator {

    private  final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    public List<RiskPremium> getRiskPremiumForAllSelectedRisk(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, getRiskPremiumForOneSelectedRisk(riskIc, request)))
                .toList();
        return riskPremiums;
    }

    private BigDecimal getRiskPremiumForOneSelectedRisk(String riskIc, TravelCalculatePremiumRequest request) {
        TravelRiskPremiumCalculator riskPremiumCalculator = getTravelRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator getTravelRiskPremiumCalculator(String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
