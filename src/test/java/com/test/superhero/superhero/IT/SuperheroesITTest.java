package com.test.superhero.superhero.IT;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SuperheroesITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpEntity<Object> getHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user", "password");

        return new HttpEntity<>(headers);
    }

    @Test
    public void retrieveAllSuperheroesTest() {

        HttpEntity<Object> objectHttpEntity = getHeader();

        ResponseEntity<List<com.superhero.models.SuperheroDTO>> response = restTemplate.exchange("/superheroes", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<List<com.superhero.models.SuperheroDTO>>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(5, response.getBody().size());

    }

    @Test
    public void retrieveSuperheroesFromNameTest() {

        HttpEntity<Object> objectHttpEntity = getHeader();

        ResponseEntity<List<com.superhero.models.SuperheroDTO>> response = restTemplate.exchange("/superheroes/findByName?name=man", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<List<com.superhero.models.SuperheroDTO>>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());

        response.getBody().forEach(superheroDTO -> Assertions.assertTrue(StringUtils.containsIgnoreCase(superheroDTO.getName(), ("man"))));

    }
}
