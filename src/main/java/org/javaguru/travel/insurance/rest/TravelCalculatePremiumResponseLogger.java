package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TravelCalculatePremiumResponseLogger {

    public void getResponseLog(TravelCalculatePremiumResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String responseLog = mapper.writeValueAsString(response);
            log.info(responseLog);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}