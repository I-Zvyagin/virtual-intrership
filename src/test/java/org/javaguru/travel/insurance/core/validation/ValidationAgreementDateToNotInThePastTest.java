package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationAgreementDateToNotInThePast;
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
class ValidationAgreementDateToNotInThePastTest {

    private ValidationAgreementDateToNotInThePast validationAgreementDateToNotInThePast = new ValidationAgreementDateToNotInThePast();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void AgreementDateToInThePast() {
        when(request.getAgreementDateTo()).thenReturn(new Date(30, 8, 25));
        Optional<ValidationError> error = validationAgreementDateToNotInThePast.execute(request);
        assertTrue(error.isPresent());
        assertEquals("agreementDateTo", error.get().getField());
        assertEquals("Must not be in the past!", error.get().getMessage());
    }

    @Test
    public void AgreementDateToInTheFuture() {
        when(request.getAgreementDateTo()).thenReturn(new Date(130, 8, 25));
        Optional<ValidationError> error = validationAgreementDateToNotInThePast.execute(request);
        assertTrue(error.isEmpty());
    }
}