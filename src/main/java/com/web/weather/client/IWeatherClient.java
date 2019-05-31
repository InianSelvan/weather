package com.web.weather.client;

import com.web.weather.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.web.weather.client.ClientConstants.*;

/**
 * API client to hit the weather api endpoint and get its JSON response, It basically
 * hits https://api.openweathermap.org/data/2.5/weather to fetch the weather data for a
 * specified country.
 *
 * @author inian
 */

@FeignClient(name = CLIENT_NAME, url = URL)
public interface IWeatherClient
{

    /**
     * Gets the weather details of a city
     * @param city
     * @param appId
     * @return the JSON weather response form api.openweathermap.org
     */
    @RequestMapping(value = RESOURCE_URI, method = RequestMethod.GET)
    public WeatherResponse getWeatherByCountry(@RequestParam(value = CITY_QUERY_PARAM) String city,
            @RequestParam(value = APPID_QUERY_PARAM) String appId);
}
