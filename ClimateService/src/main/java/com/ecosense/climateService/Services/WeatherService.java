package com.ecosense.climateService.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;

    @Value("${openweather.api-key}")
    private String apiKey;

    @Value("${openweather.url}")
    private String apiUrl;


    @Value("${openweatherLongitude.url}")
    private String locUrl;

    @Value(("${meteoweather.url}"))
    private String meteoUrl;

    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public JsonNode getCurrentWeather(String city){
        String uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q",city)
                .queryParam("appid",apiKey)
                .toUriString();

        return restTemplate.getForObject(uri,JsonNode.class);
    }

    public JsonNode getLongLatByCityName(String city){
        String uri = UriComponentsBuilder.fromHttpUrl(locUrl)
                .queryParam("q",city)
                .queryParam("appid",apiKey)
                .toUriString();

        return restTemplate.getForObject(uri,JsonNode.class);

    }

    public JsonNode getWeatherDataByDateRange(String startDate, String endDate, String cityName){
        JsonNode longLatData = getLongLatByCityName(cityName);


        Double lon = longLatData.get(0).get("lon").asDouble();
        Double lat = longLatData.get(0).get("lat").asDouble();

        String daily = "weather_code,temperature_2m_max,temperature_2m_min,temperature_2m_mean,sunrise,sunset,daylight_duration,sunshine_duration,rain_sum,snowfall_sum,wind_direction_10m_dominant,et0_fao_evapotranspiration";



        String uri = UriComponentsBuilder.fromHttpUrl(meteoUrl)
                .queryParam("latitude", lat)
                .queryParam("longitude", lon)
                .queryParam("start_date",startDate)
                .queryParam("end_date",endDate)
                .queryParam("daily",daily)
                .queryParam("timezone","auto")
                .toUriString();

        return restTemplate.getForObject(uri,JsonNode.class);

    }

}
