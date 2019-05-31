package com.web.weather.enums;

import java.util.stream.Stream;

/**
 * City enum, the constructor has readable city name and its zone id
 *
 * @author inian
 */
public enum City{

    LONDON("London", "Europe/London"),
    HONG_KONG("Hong Kong", "Hongkong");

    private String city;
    private String zoneid;

    City(String city, String zoneid){
        this.city = city;
        this.zoneid = zoneid;
    }

    public static City getCity(String name) {
        for (City category : values()) {
            if (category.toString().equals(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException(name);
    }

    public String getZoneid(){
        return zoneid;
    }

    @Override
    public String toString()
    {
        return city;
    }

    public static Stream<City> stream() {
        return Stream.of(City.values());
    }

}
