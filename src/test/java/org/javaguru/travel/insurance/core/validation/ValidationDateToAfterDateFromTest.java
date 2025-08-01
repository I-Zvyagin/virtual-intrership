package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationDateToAfterDateFrom;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationDateToAfterDateFromTest {

    private ValidationDateToAfterDateFrom validationDateToAfterDateFrom = new ValidationDateToAfterDateFrom();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void DateToBeforeDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(130, 8 , 29));
        when(request.getAgreementDateTo()).thenReturn(new Date(130, 8 , 25));
        Optional<ValidationError> error = validationDateToAfterDateFrom.execute(request);
        assertTrue(error.isPresent());
        assertEquals("agreementDateTo", error.get().getField());
        assertEquals("Must be later than agreementDateFrom!", error.get().getMessage());
    }

    @Test
    public void DateToAfterDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(130, 8 , 25));
        when(request.getAgreementDateTo()).thenReturn(new Date(130, 8 , 29));
        Optional<ValidationError> error = validationDateToAfterDateFrom.execute(request);
        assertTrue(error.isEmpty());
    }
}