package com.test.superhero.superhero.IT;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.service.constant.SuperheroConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SuperheroITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpEntity<Object> getHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user", "password");

        return new HttpEntity<>(headers);
    }

    @Test
    public void getSuperHeroById200OKTest() {

        HttpEntity<Object> objectHttpEntity = getHeader();

        ResponseEntity<com.superhero.models.SuperheroDTO> response = restTemplate.exchange("/superhero/3", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<com.superhero.models.SuperheroDTO>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Gamora", response.getBody().getName());

    }

    @Test
    public void getSuperHeroById404OKTest() {

        HttpEntity<Object> objectHttpEntity = getHeader();

        ResponseEntity<com.superhero.models.ErrorDTO> response = restTemplate.exchange("/superhero/7", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<com.superhero.models.ErrorDTO>() {
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(SuperheroConstants.SUPERHERO_NOT_FOUND, response.getBody().getDescriptionError());

    }

    @Test
    public void modifySuperHeroById200OKTest() {

        HttpEntity<Object> objectHttpEntity = getHeader();

        ResponseEntity<com.superhero.models.SuperheroDTO> response = restTemplate.exchange("/superhero/1", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<com.superhero.models.SuperheroDTO>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Superman", response.getBody().getName());

        com.superhero.models.SuperheroDTO superheroDTO = new SuperheroDTO();
        superheroDTO.setId(1l);
        superheroDTO.setName("Superwoman");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "password");

        HttpEntity<SuperheroDTO> superheroDTOHttpEntity = new HttpEntity<>(superheroDTO, headers);
        restTemplate.exchange("/superhero", HttpMethod.PUT, superheroDTOHttpEntity, new ParameterizedTypeReference<com.superhero.models.SuperheroDTO>() {
        });

        ResponseEntity<com.superhero.models.SuperheroDTO> finalResponse = restTemplate.exchange("/superhero/1", HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<com.superhero.models.SuperheroDTO>() {
        });

        Assertions.assertEquals(HttpStatus.OK, finalResponse.getStatusCode());
        Assertions.assertEquals("Superwoman", finalResponse.getBody().getName());

    }
}
