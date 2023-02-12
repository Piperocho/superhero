package com.test.superhero.superhero.IT;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SuperheroesITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void retrieveAllSuperheroesTest() {

        ResponseEntity<List<com.superhero.models.SuperheroDTO>> response = restTemplate.exchange("/superheroes", HttpMethod.GET, null, new ParameterizedTypeReference<List<com.superhero.models.SuperheroDTO>>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(5, response.getBody().size());

    }
    
    public void retrieveSuperheroesFromNameTest() {

        ResponseEntity<List<com.superhero.models.SuperheroDTO>> response = restTemplate.exchange("/superheroes/findByName?name=man", HttpMethod.GET, null, new ParameterizedTypeReference<List<com.superhero.models.SuperheroDTO>>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());

        response.getBody().forEach(superheroDTO -> Assertions.assertTrue(StringUtils.containsIgnoreCase(superheroDTO.getName(), ("man"))));

    }
}
