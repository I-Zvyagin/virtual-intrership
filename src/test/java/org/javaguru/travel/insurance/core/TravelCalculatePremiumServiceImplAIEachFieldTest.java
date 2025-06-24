package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplAIEachFieldTest {
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;
    private Date dateFrom;
    private Date dateTo;

    @BeforeEach
    public void setUp() {
        service = new TravelCalculatePremiumServiceImpl();

        dateFrom = new Date();
        dateTo = new Date(dateFrom.getTime() + 5 * 24 * 60 * 60 * 1000L); // +5 дней

        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Иван");
        request.setPersonLastName("Иванов");
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);
    }

    @Test
    public void testCalculatePremium_PersonFirstNameCopiedCorrectly() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Иван", response.getPersonFirstName(), "Имя должно быть скопировано правильно");
    }

    @Test
    public void testCalculatePremium_PersonLastNameCopiedCorrectly() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Иванов", response.getPersonLastName(), "Фамилия должна быть скопирована правильно");
    }

    @Test
    public void testCalculatePremium_AgreementDateFromCopiedCorrectly() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateFrom, response.getAgreementDateFrom(), "Дата начала должна быть скопирована правильно");
    }

    @Test
    public void testCalculatePremium_AgreementDateToCopiedCorrectly() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateTo, response.getAgreementDateTo(), "Дата окончания должна быть скопирована правильно");
    }
}