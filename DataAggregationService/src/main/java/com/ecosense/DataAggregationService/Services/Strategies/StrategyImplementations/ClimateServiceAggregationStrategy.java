package com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations;

import com.ecosense.DataAggregationService.Services.ClimateClient;
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

    @Autowired
    private ClimateClient climateClient;

    @Override
    public JsonNode aggregateData(String startDate, String endDate, Map<String, Object> additionalParams){

        String city = "";

        if(additionalParams.containsKey("city")){
            city = (String) additionalParams.getOrDefault("city", null);
        }

        if(additionalParams.containsKey("country")){
            city = (String) additionalParams.getOrDefault("country", null);
        }

        return climateClient.getClimateData(city,startDate,endDate);

    }
}
