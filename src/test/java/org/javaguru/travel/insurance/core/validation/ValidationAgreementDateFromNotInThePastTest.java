package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationAgreementDateFromNotInThePastTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationAgreementDateFromNotInThePast validationAgreementDateFromNotInThePast;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;
    @Test
    public void AgreementDateFromInThePast() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(125, 7, 25));
        when(validationErrorFactory.buildError("ERROR_CODE_4")).thenReturn(validationError);
        Optional<ValidationError> error = validationAgreementDateFromNotInThePast.execute(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void AgreementDateFromInTheFuture() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(126, 7, 25));
        Optional<ValidationError> error = validationAgreementDateFromNotInThePast.execute(request);
        assertTrue(error.isEmpty());
    }
}