package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationSelectedRisks extends ValidationServiceImpl{

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }
}
