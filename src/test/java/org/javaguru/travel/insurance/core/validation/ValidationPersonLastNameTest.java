package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationPersonLastName;
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
class ValidationPersonLastNameTest {

    private ValidationPersonLastName validationPersonLastName = new ValidationPersonLastName();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void PersonLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> error = validationPersonLastName.execute(request);
        assertTrue(error.isPresent());
        assertEquals("personLastName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void PersonLastNameIsNull() {
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> error = validationPersonLastName.execute(request);
        assertTrue(error.isPresent());
        assertEquals("personLastName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void PersonLastNameIsCorrect() {
        when(request.getPersonLastName()).thenReturn("Lee");
        Optional<ValidationError> error = validationPersonLastName.execute(request);
        assertTrue(error.isEmpty());
    }

}