package com.web.weather.client;

import com.web.weather.enums.City;
import com.web.weather.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IWeatherClientTest
{

    @Mock
    IWeatherClient iWeatherClient;

    @Test
    public void testGetWeatherByCountry(){
        Location location = new Location(City.LONDON);
        final String API_KEY = "123";
        iWeatherClient.getWeatherByCountry(location.getCity().toString(), API_KEY);
        verify(iWeatherClient).getWeatherByCountry(location.getCity().toString(), API_KEY);

    }
}
