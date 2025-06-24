package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl serviceImpl = new TravelCalculatePremiumServiceImpl();
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
}