package com.ecosense.SustainabilityService.Controllers;

import com.ecosense.SustainabilityService.Services.ForestServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sustainability")
public class ForestController {
    private final ForestServices forestService;

    public ForestController(ForestServices forestService){
        this.forestService = forestService;
    }

    @GetMapping("/forest-coverage/{region}")
    public ResponseEntity<String> getSpeciesByName(@PathVariable String region){
        String speciesData = forestService.getForestCoverage(region);
        return ResponseEntity.ok(speciesData);
    }
}
