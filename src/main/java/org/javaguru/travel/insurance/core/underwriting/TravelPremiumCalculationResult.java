package org.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.javaguru.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record TravelPremiumCalculationResult(BigDecimal totalPremium, List<RiskPremium> riskPremiums){
    public TravelPremiumCalculationResult {
        Objects.requireNonNull(totalPremium);
        Objects.requireNonNull(riskPremiums);

        riskPremiums = List.copyOf(riskPremiums);
    }
}
