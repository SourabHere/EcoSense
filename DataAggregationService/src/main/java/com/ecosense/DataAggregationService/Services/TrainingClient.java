package com.ecosense.DataAggregationService.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "fastapi-ML-microservice")
public interface TrainingClient {

    @PostMapping("/api/v1/predict")
    JsonNode trainData(@RequestBody JsonNode requestBody);

    @GetMapping("/api/v1/predict")
    JsonNode checkPort();

}
