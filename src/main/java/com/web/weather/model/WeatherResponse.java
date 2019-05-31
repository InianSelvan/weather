package com.web.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * API weather client wraps the response to this object
 *
 * @author inian
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherResponse
{
    @JsonProperty("weather")
    private List<WeatherDescription> weatherDescription;

    @JsonProperty("main")
    private Temperature temperature;

    @JsonProperty("sys")
    private HorizonEvent horizonEvent;

    @JsonProperty("timezone")
    private int           timezone;

    @JsonProperty("name")
    private String        city;

    public Temperature getTemperature() {
        return temperature;
    }

    public HorizonEvent getHorizonEvent() {
        return horizonEvent;
    }

    public int getTimezone() {
        return timezone;
    }

    public String getCity() {
        return city;
    }

    public void setTemperature(Temperature temperatureObject) {
        this.temperature = temperatureObject;
    }

    public void setHorizonEvent(HorizonEvent horizonEventObject) {
        this.horizonEvent = horizonEventObject;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public void setCity(String name) {
        this.city = name;
    }

    public List<WeatherDescription> getWeatherDescription()
    {
        return weatherDescription;
    }

    public void setWeatherDescription(List<WeatherDescription> weatherDescription)
    {
        this.weatherDescription = weatherDescription;
    }
}
