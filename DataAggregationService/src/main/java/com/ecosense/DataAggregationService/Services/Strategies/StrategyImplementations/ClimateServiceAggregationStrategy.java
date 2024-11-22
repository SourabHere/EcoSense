package com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations;

import com.ecosense.DataAggregationService.Services.Strategies.GenericAggregationStrategy;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ClimateServiceAggregationStrategy implements GenericAggregationStrategy{

    @Autowired
    private RestTemplate restTemplate;

    public final String climateApiUrl = "http://localhost:8001/api/v1/weather/historical";

    @Override
    public JsonNode aggregateData(String startDate, String endDate, Map<String, Object> additionalParams){

        String city = "";

        if(additionalParams.containsKey("city")){
            city = (String) additionalParams.getOrDefault("city", null);
        }


        String url = String.format("%s/%s?startDate=%s&endDate=%s", climateApiUrl, city, startDate, endDate);
        return restTemplate.getForObject(url, JsonNode.class);
    }
}
