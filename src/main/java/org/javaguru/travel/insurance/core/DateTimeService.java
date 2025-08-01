package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class DateTimeService {
    long getPeriodInDays(Date date1, Date date2) {
        long dateMax = Math.max(date2.getTime(),date1.getTime());
        long dateMin = Math.min(date2.getTime(),date1.getTime());
        long milliseconds = dateMax - dateMin;
        return TimeUnit.DAYS.convert(milliseconds, TimeUnit.MILLISECONDS);
    }
}