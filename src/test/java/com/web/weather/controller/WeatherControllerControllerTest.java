package com.web.weather.controller;

import com.web.weather.WeatherApplication;
import com.web.weather.enums.City;
import com.web.weather.model.Location;
import com.web.weather.model.WeatherDto;
import com.web.weather.service.IWeatherSvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WeatherApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class WeatherControllerControllerTest
{

    private MockMvc mockMvc;

    @Mock IWeatherSvc weatherSvc;

    @InjectMocks
    WeatherController weatherController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void testIndexPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("location"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(view().name("index.html"));
    }

    @Test
    public void testWeatherSubmit() throws Exception {
        final Location location = new Location(City.LONDON);
        final String API_KEY = null;

        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setDate(LocalDate.now());
        weatherDto.setCity("London");
        weatherDto.setFahrenheit(77);
        weatherDto.setCelsius(170);
        weatherDto.setSunrise("07:40:37 AM");
        weatherDto.setSunset("04:47:55 PM");
        weatherDto.setDescription("Rain");
        when(weatherSvc.getWeatherDetails(any(), any())).thenReturn(weatherDto);

        this.mockMvc.perform(get("/weather").param("city","London"))
                .andExpect(status().isOk())
                .andExpect(view().name("weather.html"));
    }
}
