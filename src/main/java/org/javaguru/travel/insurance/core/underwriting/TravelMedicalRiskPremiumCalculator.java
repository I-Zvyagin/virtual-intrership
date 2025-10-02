package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator{

    private final DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal calculatePremium (TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
