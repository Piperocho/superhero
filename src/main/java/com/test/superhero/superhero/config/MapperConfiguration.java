package com.test.superhero.superhero.config;

import com.test.superhero.superhero.mapper.SuperheroMapper;
import com.test.superhero.superhero.mapper.SuperheroMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public SuperheroMapper superheroMapper() {
        return new SuperheroMapperImpl();
    }
}
