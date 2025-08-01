package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.core.validation.ValidationService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new TravelCalculatePremiumRequestValidatorImpl(List.of(validationService));
    }

    @Test
    void validationIsSuccessful() {
        when(validationService.execute(request)).thenReturn(Optional.empty());
        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    void validationIsUnsuccessful() {
        when(validationService.execute(request)).thenReturn(Optional.of(new ValidationError()));
        assertFalse(validator.validate(request).isEmpty());
    }

}