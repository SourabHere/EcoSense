package com.ecosense.DataAggregationService.Services.Strategies;

import com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations.BiodiversityServiceAggregationStrategy;
import com.ecosense.DataAggregationService.Services.Strategies.StrategyImplementations.ClimateServiceAggregationStrategy;
import com.ecosense.DataAggregationService.Services.TrainingClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AggregationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClimateServiceAggregationStrategy climateStrategy;

    @Autowired
    private TrainingClient trainingClient;

    @Autowired
    private BiodiversityServiceAggregationStrategy biodiversityStrategy;

    public JsonNode getAggregatedData(String startDate, String endDate, Map<String, Object> additionalParams){
        JsonNode climateData = climateStrategy.aggregateData(startDate,endDate,additionalParams);
        JsonNode biodiversityData = biodiversityStrategy.aggregateData(startDate,endDate,additionalParams);

        ObjectNode combinedResponse = new ObjectNode(new com.fasterxml.jackson.databind.node.JsonNodeFactory(false));

        combinedResponse.set("climate", climateData);
        combinedResponse.set("biodiversity", biodiversityData);

        return combinedResponse;
    }


    public JsonNode trainAggregatedData(String startDate, String endDate, Map<String, Object> additionalParams) {
        JsonNode climateData = climateStrategy.aggregateData(startDate, endDate, additionalParams);
        JsonNode biodiversityData = biodiversityStrategy.aggregateData(startDate, endDate, additionalParams);
        

        ObjectNode combinedResponse = new ObjectNode(new com.fasterxml.jackson.databind.node.JsonNodeFactory(false));
        combinedResponse.set("climate", climateData);
        combinedResponse.set("biodiversity", biodiversityData);
        combinedResponse.put("startDate", startDate);
        combinedResponse.put("endDate", endDate);

        JsonNode res = trainingClient.trainData(combinedResponse);
        return res;
    }


}
