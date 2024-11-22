package com.ecosense.climateService.Controllers;

import com.ecosense.climateService.Services.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/current/{city}")
    public ResponseEntity<JsonNode> getCurrentWeather(@PathVariable String city){
        JsonNode weatherData = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(weatherData);
    }

    @GetMapping("/location/{city}")
    public ResponseEntity<JsonNode> getLongitudeLatiduteByCity(@PathVariable String city){
        JsonNode locationData = weatherService.getLongLatByCityName(city);
        return ResponseEntity.ok(locationData);
    }

    @GetMapping("/historical/{city}")
    public ResponseEntity<JsonNode>  getWeatherDataByDateRange(
            @PathVariable String city,
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate
    ){
        JsonNode combinedData = weatherService.getWeatherDataByDateRange(startDate,endDate,city);
        return ResponseEntity.ok(combinedData);
    }

}
