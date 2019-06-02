package com.web.weather.exception;

public class LocationNotFountException extends RuntimeException
{
    public LocationNotFountException(String message){
        super(message);
    }
}
