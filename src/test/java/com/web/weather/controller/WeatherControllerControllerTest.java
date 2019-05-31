package com.web.weather.controller;

import com.web.weather.WeatherApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WeatherApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class WeatherControllerControllerTest
{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testIndexPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("location"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(content().string(containsString("London")))
                .andExpect(content().string(containsString("Hong Kong")))
                .andExpect(content().string(containsString("submit")))
                .andExpect(view().name("index"));
    }

    @Test
    public void testWeatherSubmit() throws Exception {
        this.mockMvc.perform(get("/weather?city=London"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
