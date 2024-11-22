package com.ecosense.DataAggregationService.Controllers;

import com.ecosense.DataAggregationService.Services.Strategies.AggregationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dataAggregation")
public class AggregationController {

    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/history")
    public ResponseEntity<JsonNode> getAggregatedData(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String scientificName
    ) {

        Map<String, Object> additionalParams = new HashMap<>();

        if (scientificName != null) {
            additionalParams.put("scientificName", scientificName);
        }

        if (country != null){
            additionalParams.put("country", country);
        }


        JsonNode combinedData = aggregationService.getAggregatedData(startDate,endDate,additionalParams);

        return ResponseEntity.ok(combinedData);
    }

}
