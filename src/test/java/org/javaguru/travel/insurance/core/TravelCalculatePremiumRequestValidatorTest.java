package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {
    @Mock
    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumRequestValidator validator;
    private List<ValidationError> testList;

    @BeforeEach
    void setUp() {
       validator = new TravelCalculatePremiumRequestValidator();
       testList = new ArrayList<>();
    }

    @Test
    void validateIfAllFieldsFilled() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,20));
        testList = validator.validate(request);
        assertEquals(0, testList.size());
    }

    @Test
    void validateIfPersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,20));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("personFirstName", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfPersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,20));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("personFirstName", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfPersonLastNameIsNull() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,20));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("personLastName", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfPersonLastNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,20));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("personLastName", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfDateFromIsNull() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("agreementDateFrom", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfDateTomIsNull() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(null);
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("agreementDateTo", testList.get(0).getField());
        assertEquals("Must not be empty!", testList.get(0).getMessage());
    }

    @Test
    void validateIfDateToEqualsDateFrom() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,15));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,15));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("agreementDateTo", testList.get(0).getField());
        assertEquals("Must be later then agreementDateFrom!", testList.get(0).getMessage());
    }

    @Test
    void validateIfDateToBeforeDateFrom() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        when(request.getPersonLastName()).thenReturn("Lee");
        when(request.getAgreementDateFrom()).thenReturn(new Date(50,5,20));
        when(request.getAgreementDateTo()).thenReturn(new Date(50,5,15));
        testList = validator.validate(request);
        assertEquals(1, testList.size());
        assertEquals("agreementDateTo", testList.get(0).getField());
        assertEquals("Must be later then agreementDateFrom!", testList.get(0).getMessage());
    }
}