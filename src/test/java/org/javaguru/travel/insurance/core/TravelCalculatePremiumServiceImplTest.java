package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl serviceImpl;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        serviceImpl = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    private TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest
            ("Stan",
                    "Lee",
                    new Date(120, 4, 20),
                    new Date(120, 4, 27));

    @Test
    public void correctFirstNameFilling() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertEquals(responseFromMethod.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void correctLastNameFilling() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertEquals(responseFromMethod.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void correctDateFromFilling() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertEquals(responseFromMethod.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void correctDateToFilling() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertEquals(responseFromMethod.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void notNullAgreementPrice() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertNotNull(responseFromMethod.getAgreementPrice());
    }

    @Test
    public void correctAgreementPriceCalculating() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium(request);
        assertEquals(responseFromMethod.getAgreementPrice(),new BigDecimal(dateTimeService.getPeriodInDays
                        (request.getAgreementDateFrom(),request.getAgreementDateTo())));
    }
}