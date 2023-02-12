package com.test.superhero.superhero.IT;

import com.test.superhero.superhero.service.constant.SuperheroConstants;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SuperheroITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getSuperHeroById200OKTest() {

        ResponseEntity<com.superhero.models.SuperheroDTO> response = restTemplate.exchange("/superhero/3", HttpMethod.GET, null, new ParameterizedTypeReference<com.superhero.models.SuperheroDTO>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Gamora", response.getBody().getName());

    }
    
    public void getSuperHeroById404OKTest() {

        ResponseEntity<com.superhero.models.ErrorDTO> response = restTemplate.exchange("/superhero/7", HttpMethod.GET, null, new ParameterizedTypeReference<com.superhero.models.ErrorDTO>() {
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(SuperheroConstants.SUPERHERO_NOT_FOUND, response.getBody().getDescriptionError());

    }
}
