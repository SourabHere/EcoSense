package com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations;

import com.ecosense.DataAggregationService.Services.Strategies.GenericAggregationStrategy;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class BiodiversityServiceAggregationStrategy implements GenericAggregationStrategy {
    @Autowired
    private RestTemplate restTemplate;

    private final String biodiversityApiUrl = "http://localhost:8002/api/v1/biodiversity/species/population";

    @Override
    public JsonNode aggregateData(String startDate, String endDate, Map<String, Object> additionalParams){
        String country = (String) additionalParams.getOrDefault("country", null);
        String url = String.format("%s?startDate=%s&endDate=%s&country=%s", biodiversityApiUrl, startDate, endDate, country);
        return restTemplate.getForObject(url, JsonNode.class);
    }
}
