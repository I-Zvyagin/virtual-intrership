package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplAIOneTest {
    @Test
    public void testCalculatePremium_CopiesFieldsCorrectly() {
        // Arrange
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Иван");
        request.setPersonLastName("Иванов");

        Date dateFrom = new Date();
        Date dateTo = new Date(dateFrom.getTime() + 5 * 24 * 60 * 60 * 1000L); // +5 дней

        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        // Act
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert
        assertEquals("Иван", response.getPersonFirstName());
        assertEquals("Иванов", response.getPersonLastName());
        assertEquals(dateFrom, response.getAgreementDateFrom());
        assertEquals(dateTo, response.getAgreementDateTo());
    }
}