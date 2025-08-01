package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationPersonFirstName;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationPersonFirstNameTest {

    private ValidationPersonFirstName validationPersonFirstName = new ValidationPersonFirstName();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void PersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> error = validationPersonFirstName.execute(request);
        assertTrue(error.isPresent());
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void PersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validationPersonFirstName.execute(request);
        assertTrue(error.isPresent());
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void PersonFirstNameIsCorrect() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        Optional<ValidationError> error = validationPersonFirstName.execute(request);
        assertTrue(error.isEmpty());
    }
}