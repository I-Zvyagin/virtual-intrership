package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class UnderwritingServiceImpl implements UnderwritingService {

    private DateTimeService dateTimeService;

    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        dateTimeService = new DateTimeService();
        return new BigDecimal(dateTimeService.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
}