package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.core.validation.ValidationAgreementDateTo;
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
class ValidationAgreementDateToTest {

    private ValidationAgreementDateTo validationAgreementDateTo = new ValidationAgreementDateTo();

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void AgreementDateToIsNull() {
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validationAgreementDateTo.execute(request);
        assertTrue(error.isPresent());
        assertEquals("agreementDateTo", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    public void AgreementDateToIsCorrect() {
        when(request.getAgreementDateTo()).thenReturn(new Date(130, 8, 29));
        Optional<ValidationError> error = validationAgreementDateTo.execute(request);
        assertTrue(error.isEmpty());
    }
}