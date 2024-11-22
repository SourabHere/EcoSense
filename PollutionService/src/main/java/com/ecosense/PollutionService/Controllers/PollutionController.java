package com.ecosense.PollutionService.Controllers;

import com.ecosense.PollutionService.Services.PollutionDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pollution")
public class PollutionController {
    private final PollutionDataService pollutionDataService;

    public PollutionController(PollutionDataService pollutionDataService) {
        this.pollutionDataService = pollutionDataService;
    }


    @GetMapping("/city/{city}")
    public ResponseEntity<String> getAirQualityByCity(@PathVariable String city) {
        String pollutionData = pollutionDataService.getAirQualityByCity(city);
        return ResponseEntity.ok(pollutionData);
    }

}
