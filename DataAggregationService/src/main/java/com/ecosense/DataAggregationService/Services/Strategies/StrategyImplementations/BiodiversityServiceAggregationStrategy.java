package com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations;

import com.ecosense.DataAggregationService.Services.BioClient;
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

    @Autowired
    private BioClient bioClient;

    @Override
    public JsonNode aggregateData(String startDate, String endDate, Map<String, Object> additionalParams){
        String country = (String) additionalParams.getOrDefault("country", null);
        return bioClient.getPopulationData(startDate, endDate, country);
    }
}
