package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{

    private final List<ValidationService> validationServices;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request){
        List<ValidationError> list1 = collectOneError(request);
        List<ValidationError> list2 = collectListOfErrors(request);
        return concatLists(list1, list2);

    }
    public List<ValidationError> collectOneError(TravelCalculatePremiumRequest request) {
        return validationServices.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<ValidationError> collectListOfErrors(TravelCalculatePremiumRequest request) {
        return validationServices.stream()
                .flatMap(validation -> validation.validateList(request).stream())
                .collect(toList());
    }

    public List<ValidationError> concatLists(List<ValidationError> list1, List<ValidationError> list2) {
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}