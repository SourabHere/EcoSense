package com.ecosense.PollutionService.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PollutionDataService {

    private final RestTemplate restTemplate;

    @Value("${openaq.url}")
    private String apiUrl;

    @Value("${openaq.apiKey}")
    private String apiKey;

    public PollutionDataService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public String getAirQualityByCity(String city){

    }

}
