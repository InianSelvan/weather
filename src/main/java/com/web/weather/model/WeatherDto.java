package com.web.weather.model;

import java.time.LocalDate;

/**
 * This Object is used to transfer the client response to application specific
 * weather data
 *
 * @author inian
 */
public class WeatherDto
{
    private LocalDate date;

    private String city;

    private String description;

    private double celsius;

    private double fahrenheit;

    private String  sunrise;

    private String  sunset;

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getCelsius()
    {
        return celsius;
    }

    public void setCelsius(double celsius)
    {
        this.celsius = celsius;
    }

    public double getFahrenheit()
    {
        return fahrenheit;
    }

    public void setFahrenheit(double fahrenheit)
    {
        this.fahrenheit = fahrenheit;
    }

    public String getSunrise()
    {
        return sunrise;
    }

    public void setSunrise(String sunrise)
    {
        this.sunrise = sunrise;
    }

    public String getSunset()
    {
        return sunset;
    }

    public void setSunset(String sunset)
    {
        this.sunset = sunset;
    }
}
