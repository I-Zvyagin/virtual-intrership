package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationSelectedRisksTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationSelectedRisks validationSelectedRisks;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;

    @Test
    public void ValidationSelectedRisksIsNull() {
        when(request.getSelectedRisks()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> error = validationSelectedRisks.execute(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void ValidationSelectedRisksIsCorrect() {
        when(request.getSelectedRisks()).thenReturn(List.of(
                "TRAVEL_MEDICAL",
                "TRAVEL_CANCELLATION",
                "TRAVEL_LOSS_BAGGAGE"));
        Optional<ValidationError> error = validationSelectedRisks.execute(request);
        assertTrue(error.isEmpty());
    }
}