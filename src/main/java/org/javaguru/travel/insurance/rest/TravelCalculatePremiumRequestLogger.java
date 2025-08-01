package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumRequestLogger {

    public void getRequestLog(TravelCalculatePremiumRequest request){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String requestLog = mapper.writeValueAsString(request);
            log.info(requestLog);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}