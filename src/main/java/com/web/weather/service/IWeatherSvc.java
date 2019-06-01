package com.web.weather.service;

import com.web.weather.model.Location;
import com.web.weather.model.WeatherDto;
import org.springframework.stereotype.Service;

@Service
public interface IWeatherSvc
{
    public WeatherDto getWeatherDetails(Location location, String key);
}
