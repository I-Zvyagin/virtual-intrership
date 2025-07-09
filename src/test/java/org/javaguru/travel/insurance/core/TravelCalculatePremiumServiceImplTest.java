package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse response;

    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
        when(dateTimeService.getPeriodInDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(request)).thenReturn(List.of());
        response = service.calculatePremium(request);
    }

    @Test
    public void correctFirstNameFilling(){
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void correctLastNameFilling(){
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void correctAgreementDateTo(){
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void correctAgreementDateFrom(){
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void correctAgreementPrice(){
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Stan");
        request.setPersonLastName("Lee");
        request.setAgreementDateFrom(new Date(120, 5, 20));
        request.setAgreementDateTo(new Date(120, 5, 25));
        return request;
    }
}