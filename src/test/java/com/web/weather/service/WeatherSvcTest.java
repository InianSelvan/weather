package com.web.weather.service;

import com.web.weather.client.IWeatherClient;
import com.web.weather.enums.City;
import com.web.weather.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherSvcTest
{
    @InjectMocks
    WeatherSvc weatherSvc;

    @Mock
    IWeatherClient iWeatherClient;

    @Test
    public void getWeatherDetailsTest(){
        Location location = new Location(City.LONDON);
        final String API_KEY = "123";
        WeatherResponse weatherResponse = new WeatherResponse();
        WeatherDescription weatherDescription = new WeatherDescription();
        weatherDescription.setDescription("Rain");
        Temperature temperature = new Temperature();
        temperature.setTemp(350.04);
        HorizonEvent horizonEvent = new HorizonEvent();
        horizonEvent.setCountry("Canada");
        horizonEvent.setSunrise(1485762037l);
        horizonEvent.setSunset(1485794875);

        weatherResponse.setCity("London");
        weatherResponse.setHorizonEvent(horizonEvent);
        weatherResponse.setWeatherDescription(Arrays.asList(weatherDescription));
        weatherResponse.setTemperature(temperature);

        when(iWeatherClient.getWeatherByCountry(location.getCity().toString(), API_KEY)).thenReturn(weatherResponse);
        WeatherDto weatherDto = weatherSvc.getWeatherDetails(location, API_KEY);

        verify(iWeatherClient).getWeatherByCountry(location.getCity().toString(), API_KEY);

        assertEquals(LocalDate.of(2019,05,31), weatherDto.getDate());
        assertEquals(City.LONDON.toString(), weatherDto.getCity());
        assertEquals("Rain", weatherDto.getDescription());
        assertTrue(weatherDto.getCelsius()==77.0);
        assertTrue(weatherDto.getFahrenheit()==170.6);
        assertEquals("07:40:37 AM", weatherDto.getSunrise());
        assertEquals("04:47:55 PM", weatherDto.getSunset());
    }

    @Test
    public void getWeatherDetailsResponseNullTest(){
        Location location = new Location(City.LONDON);
        final String API_KEY = "123";

        when(iWeatherClient.getWeatherByCountry(location.getCity().toString(), API_KEY)).thenReturn(null);
        WeatherDto weatherDto = weatherSvc.getWeatherDetails(location, API_KEY);

        verify(iWeatherClient).getWeatherByCountry(location.getCity().toString(), API_KEY);

        assertEquals(null, weatherDto.getDate());
        assertEquals(null, weatherDto.getCity());
        assertEquals(null, weatherDto.getDescription());
        assertEquals(null, weatherDto.getSunrise());
        assertEquals(null, weatherDto.getSunset());
    }
}
