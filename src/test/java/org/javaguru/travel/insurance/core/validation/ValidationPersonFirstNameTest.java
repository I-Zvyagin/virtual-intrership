package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationPersonFirstNameTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationPersonFirstName validationPersonFirstName;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;


    @Test
    public void PersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> error = validationPersonFirstName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void PersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> error = validationPersonFirstName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void PersonFirstNameIsCorrect() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        Optional<ValidationError> error = validationPersonFirstName.validate(request);
        assertTrue(error.isEmpty());
    }
}