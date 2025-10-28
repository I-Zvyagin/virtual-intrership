package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;
    private final CountryDefaultDayRateRepository dayRate;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return getDayCount(request).multiply(getCountryDefaultDayPremium(request)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getCountryDefaultDayPremium(TravelCalculatePremiumRequest request) {
        return dayRate.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country ic = " + request.getCountry()));
    }

    private BigDecimal getDayCount(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
