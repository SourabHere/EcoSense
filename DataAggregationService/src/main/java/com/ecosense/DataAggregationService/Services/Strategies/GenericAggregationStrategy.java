package com.ecosense.DataAggregationService.Services.Strategies;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface GenericAggregationStrategy {
    JsonNode aggregateData(String startDate, String endDate, Map<String, Object> additionalParams);
}
