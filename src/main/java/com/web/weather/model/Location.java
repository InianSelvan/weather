package com.web.weather.model;

import com.web.weather.enums.City;

/**
 * Location of the city
 *
 * @author inian
 */
public class Location
{

    private City city;

    public Location()
    {
    }

    public Location(City city)
    {
        this.city = city;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(String city)
    {

        this.city = City.getCity(city);
    }

}

