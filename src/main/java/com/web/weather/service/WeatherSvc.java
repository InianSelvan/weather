package com.web.weather.service;

import com.web.weather.client.IWeatherClient;
import com.web.weather.model.Location;
import com.web.weather.model.WeatherDto;
import com.web.weather.model.WeatherResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.web.weather.service.ServiceConstants.HOUR_PATTERN;
import static com.web.weather.service.ServiceConstants.KELVIN;

/**
 * @author inian
 */
@Service
public class WeatherSvc
{

    @Autowired
    IWeatherClient iWeatherClient;



    /**
     * The WeatherController details obtained from the API end point is then mapped to WeatherDto object
     * @param location
     * @param key
     * @throws FeignException
     * @return WeatherController DTO object
     */
    public WeatherDto getWeatherDetails(Location location, String key) throws FeignException
    {
        WeatherResponse response = iWeatherClient.getWeatherByCountry(location.getCity().toString(), key);
        if(response != null){
            WeatherDto weatherDto = new WeatherDto();
            weatherDto.setCity(response.getCity());
            weatherDto.setDate(Instant.now().atZone(ZoneId.of(location.getCity().getZoneid())).toLocalDate());

            //set weather description
            if(response.getWeatherDescription() != null && response.getWeatherDescription().size() > 0){
                weatherDto.setDescription(response.getWeatherDescription().get(0).getDescription());
            }

            //set sun rise and set
            if(response.getHorizonEvent() != null){
                String localSunriseTime = Instant.ofEpochSecond(response.getHorizonEvent().getSunrise())
                        .atZone(ZoneId.of(location.getCity().getZoneid())).toLocalTime().format(DateTimeFormatter.ofPattern(HOUR_PATTERN));
                String localSunsetTime = Instant.ofEpochSecond(response.getHorizonEvent().getSunset())
                        .atZone(ZoneId.of(location.getCity().getZoneid())).toLocalTime().format(DateTimeFormatter.ofPattern(HOUR_PATTERN));
                weatherDto.setSunrise(localSunriseTime);
                weatherDto.setSunset(localSunsetTime);
            }

            //set temperature
            if(response.getTemperature() != null)
            {
                double celsius = Math.floor(((response.getTemperature().getTemp() - KELVIN) * 100)/100);
                weatherDto.setCelsius(celsius);
                weatherDto.setFahrenheit(Math.floor(((celsius * 9.0/5.0) + 32.0)*100)/100);
            }
            return weatherDto;

        }
        return new WeatherDto();
    }
}
