package com.web.weather.model;

/**
 * Contains temperature info
 *
 * @author inian
 */
public class Temperature
{
    private double temp;
    private double temp_min;
    private double temp_max;

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
