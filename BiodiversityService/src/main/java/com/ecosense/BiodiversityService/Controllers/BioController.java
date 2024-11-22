package com.ecosense.BiodiversityService.Controllers;

import com.ecosense.BiodiversityService.Services.BioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/biodiversity")
public class BioController {

    private final BioService bioService;

    public BioController(BioService bioService){
        this.bioService = bioService;
    }

    @GetMapping("/species/{name}")
    public ResponseEntity<JsonNode> getSpeciesByName(@PathVariable String name){
        JsonNode speciesData = bioService.getSpeciesByName(name);
        return ResponseEntity.ok(speciesData);
    }

    @GetMapping("/species/population")
    public ResponseEntity<JsonNode> getSpeciesPopulation(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false) String scientificName,
            @RequestParam(required = false) String country) {

        JsonNode response = bioService.getSpeciesDataByDate(startDate,endDate,scientificName,country);

        return ResponseEntity.ok(response);
    }

}
