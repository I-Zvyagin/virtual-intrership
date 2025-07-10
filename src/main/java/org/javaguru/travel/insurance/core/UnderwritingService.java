package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface UnderwritingService {
    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
