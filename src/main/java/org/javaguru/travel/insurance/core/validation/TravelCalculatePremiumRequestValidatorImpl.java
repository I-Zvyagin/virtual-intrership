package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{

    private final List<ValidationService> validationServices;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return validationServices.stream()
                .map(validation -> validation.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}