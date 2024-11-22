package com.ecosense.SustainabilityService.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ForestServices {
    private final RestTemplate restTemplate;

    @Value("${gfw.url}")
    private String gfwUrl;

    public ForestServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getForestCoverage(String region) {
        String uri = UriComponentsBuilder.fromHttpUrl(gfwUrl + "/forest-coverage")
                .queryParam("region", region)
                .toUriString();

        return uri;
    }

}
