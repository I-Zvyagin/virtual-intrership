package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationAgreementDateFromNotInThePast extends ValidationServiceImpl{

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date now = Date.from(java.time.ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
        return (request.getAgreementDateFrom() != null &&
                request.getAgreementDateFrom().before(now))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
}