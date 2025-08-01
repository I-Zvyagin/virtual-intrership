package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationAgreementDateFrom;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationAgreementDateFromTest {

    private ValidationAgreementDateFrom validationAgreementDateFrom = new ValidationAgreementDateFrom();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void AgreementDateFromIsNull() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validationAgreementDateFrom.execute(request);
        assertTrue(error.isPresent());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void AgreementDateFromIsCorrect() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(130, 8, 25));
        Optional<ValidationError> error = validationAgreementDateFrom.execute(request);
        assertTrue(error.isEmpty());
    }
}