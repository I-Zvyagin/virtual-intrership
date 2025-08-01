package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonResponseComparator {
    public boolean areJsonEqual(String json1, String json2) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tree1 = mapper.readTree(json1);
            JsonNode tree2 = mapper.readTree(json2);
            return tree1.equals(tree2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
