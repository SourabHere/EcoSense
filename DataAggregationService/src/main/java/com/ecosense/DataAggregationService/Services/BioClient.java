package com.ecosense.DataAggregationService.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BiodiversityService")
public interface BioClient {

    @GetMapping("/api/v1/biodiversity/species/population?startDate={startDate}&endDate={endDate}&country={country}")
    JsonNode getPopulationData(@PathVariable String startDate,@PathVariable String endDate,@PathVariable String country);
}
