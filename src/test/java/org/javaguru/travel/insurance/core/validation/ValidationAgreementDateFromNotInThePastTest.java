package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationAgreementDateFromNotInThePast;
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
class ValidationAgreementDateFromNotInThePastTest {

    private ValidationAgreementDateFromNotInThePast validationAgreementDateFromNotInThePast = new ValidationAgreementDateFromNotInThePast();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void AgreementDateFromInThePast() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(30, 8, 25));
        Optional<ValidationError> error = validationAgreementDateFromNotInThePast.execute(request);
        assertTrue(error.isPresent());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Must not be in the past!", error.get().getMessage());
    }

    @Test
    public void AgreementDateFromInTheFuture() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(130, 8, 25));
        Optional<ValidationError> error = validationAgreementDateFromNotInThePast.execute(request);
        assertTrue(error.isEmpty());
    }
}