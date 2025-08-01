package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
class ValidationAgreementDateFromNotInThePast implements ValidationService{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date now = Date.from(java.time.ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
        return (request.getAgreementDateFrom() != null &&
                request.getAgreementDateFrom().before(now))
                ? Optional.of(new ValidationError("agreementDateFrom",
                "Must not be in the past!"))
                : Optional.empty();
    }
}
