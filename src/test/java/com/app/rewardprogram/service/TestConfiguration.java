package com.app.rewardprogram.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class TestConfiguration {

    @Bean
    public Clock clock() {
        return Mockito.mock(Clock.class);
    }
}
