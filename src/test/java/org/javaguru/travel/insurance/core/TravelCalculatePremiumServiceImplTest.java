package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl serviceImpl = new TravelCalculatePremiumServiceImpl();
    @Test
    public void correctFieldsFilling() {
        TravelCalculatePremiumResponse responseFromMethod = serviceImpl.calculatePremium
                (new TravelCalculatePremiumRequest("Stan", "Lee",
                        new Date(120, 4, 20), new Date(120, 4, 27)));

        assertEquals(responseFromMethod.getPersonFirstName(), "Stan");
        assertEquals(responseFromMethod.getPersonLastName(), "Lee");
        assertEquals(responseFromMethod.getAgreementDateFrom(), new Date(120, 4, 20));
        assertEquals(responseFromMethod.getAgreementDateTo(), new Date(120, 4, 27));
    }
}