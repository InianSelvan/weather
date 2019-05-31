package com.web.weather.controller;

import com.web.weather.enums.City;
import com.web.weather.model.Location;
import com.web.weather.model.WeatherDto;
import com.web.weather.service.WeatherSvc;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.stream.Collectors;

import static com.web.weather.controller.ControllerConstants.*;

/**
 * This is a weather controller to pass the view to the template
 *
 * @author inian
 */
@Controller
public class WeatherController
{
    @Autowired
    private WeatherSvc weatherSvc;

    @Value("${api.id}")
    private String key;

    @Value("${error.message}")
    private String errorMessage;

    @Value("${error.message.client}")
    private String errorMessageClient;

    /**
     * Select the country and then submit to weather action to get the weather details of the
     * selected country
     *
     * @param model
     * @return the index page
     */
    @GetMapping(BASE_PATH)
    public String selectCountry(Model model) {
        Location location = new Location();
        model.addAttribute(LOCATION, location);
        model.addAttribute(CITIES, City.stream().map(e->e.toString()).collect(Collectors.toList()));

        return INDEX_PAGE;
    }

    /**
     * Get the weather details of the passed country
     *
     * @param model
     * @param location
     * @return the weather details page
     */
    @GetMapping(WEATHER_PATH)
    public String getWeather(Model model, @ModelAttribute(LOCATION) Location location, final BindingResult result) {
        WeatherDto weatherDto = null;
        if (result.hasErrors() || location == null) {
            model.addAttribute(ERROR_MESSAGE_PROP, errorMessage);
            selectCountry(model);
            return INDEX_PAGE;
        }else{
            try{
                weatherDto = weatherSvc.getWeatherDetails(location, key);
            }catch (FeignException e){
                model.addAttribute(ERROR_MESSAGE_PROP, errorMessageClient);
                selectCountry(model);
                return INDEX_PAGE;
            }
            if(weatherDto != null){
                model.addAttribute(WEATHER_DETAILS, weatherDto);
            }
        }
        return WEATHER_PAGE;
    }

}
