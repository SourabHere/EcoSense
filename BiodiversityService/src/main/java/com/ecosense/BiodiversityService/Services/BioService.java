package com.ecosense.BiodiversityService.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BioService {
    private final RestTemplate restTemplate;

    @Value("${gbif.url}")
    private String apiUrl;

    public BioService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public JsonNode getSpeciesByName(String speciesName){
        String speciesUrl = apiUrl + "/species";
        String uri = UriComponentsBuilder.fromHttpUrl(speciesUrl)
                .queryParam("name",speciesName)
                .toUriString();

        return restTemplate.getForObject(uri, JsonNode.class);
    }

    public JsonNode getSpeciesDataByDate(String startDate, String endDate, String scientificName, String country){

        String occuranceApiUrl = apiUrl + "/occurrence/search";

        String overallAPIUrl = occuranceApiUrl + "?eventDate=" + startDate + "," + endDate;

        if (scientificName != null) {
            overallAPIUrl += "&scientificName=" + scientificName;
        }
        if (country != null) {
            overallAPIUrl += "&country=" + country;
        }


        String uri = UriComponentsBuilder.fromHttpUrl(overallAPIUrl)
                .toUriString();

        JsonNode response = restTemplate.getForObject(uri, JsonNode.class);

        return response;
    }

}
