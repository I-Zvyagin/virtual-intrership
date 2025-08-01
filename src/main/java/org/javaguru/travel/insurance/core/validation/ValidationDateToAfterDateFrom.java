package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidationDateToAfterDateFrom implements ValidationService{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() != null &&
                request.getAgreementDateFrom() != null &&
                (request.getAgreementDateFrom().after(request.getAgreementDateTo()) ||
                        request.getAgreementDateTo().getTime() == request.getAgreementDateFrom().getTime()))
                ? Optional.of(new ValidationError("agreementDateTo",
                "Must be later than agreementDateFrom!"))
                : Optional.empty();
    }
}
