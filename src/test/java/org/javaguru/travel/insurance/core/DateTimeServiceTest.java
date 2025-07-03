package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeServiceTest {
    private Date date1;
    private  Date date2;
    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void getCorrectAmountOfDays1() {
        date1 = new Date(120,10,20);
        date2 = new Date(120,10,25);
        assertEquals(5L, (dateTimeService.getPeriodInDays(date1, date2)));
    }

    @Test
    public void getCorrectAmountOfDays2() {
        date1 = new Date(120,10,25);
        date2 = new Date(120,10,20);
        assertEquals(5L, (dateTimeService.getPeriodInDays(date1, date2)));
    }

    @Test
    public void getZeroDays() {
        date1 = new Date(120,10,20);
        date2 = new Date(120,10,20);
        assertEquals(0L, (dateTimeService.getPeriodInDays(date1, date2)));
    }
}
