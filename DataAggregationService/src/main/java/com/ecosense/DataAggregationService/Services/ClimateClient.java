package com.ecosense.DataAggregationService.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "climateService")
public interface ClimateClient {
    @GetMapping("/api/v1/weather/historical/{city}?startDate={startDate}&endDate={endDate}")
    JsonNode getClimateData(@PathVariable String city, @PathVariable String startDate, @PathVariable String endDate);

}
