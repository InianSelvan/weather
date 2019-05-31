package com.web.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Horizon Event contains sun raise and sun set information
 *
 * @author inian
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorizonEvent
{
    private String country;
    private long sunrise;
    private long sunset;

    public String getCountry() {
        return country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
