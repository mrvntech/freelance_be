package com.example.freelance_be.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Common {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
