package com.test.superhero.superhero.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.superhero.superhero.api.Exception.EntityNotFoundException;
import com.test.superhero.superhero.service.SuperheroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SuperheroControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    SuperheroService superheroService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getSuperheroesFromId200OKTest() throws Exception {
        com.superhero.models.SuperheroDTO superheroExpected = new com.superhero.models.SuperheroDTO();
        superheroExpected.setId(1l);
        superheroExpected.setName("Superman");

        Mockito.when(superheroService.getSuperheroById(1l)).thenReturn(superheroExpected);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superhero/1")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        ).andReturn();

        final String stringResponse = result.getResponse().getContentAsString();
        final com.superhero.models.SuperheroDTO superheroReturned = this.objectMapper.readValue(stringResponse, new TypeReference<com.superhero.models.SuperheroDTO>() {
        });

        Assertions.assertEquals(superheroExpected.getId(), superheroReturned.getId());
        Assertions.assertEquals(superheroExpected.getName(), superheroReturned.getName());
    }

    @Test
    public void getSuperheroesFromId404KOTest() throws Exception {

        EntityNotFoundException entityNotFoundException = new EntityNotFoundException("Could not find the superhero");

        Mockito.when(superheroService.getSuperheroById(2l)).thenThrow(entityNotFoundException);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superhero/2")
        ).andExpect(
                status().is4xxClientError()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        ).andReturn();

        final String stringResponse = result.getResponse().getContentAsString();
        final com.superhero.models.ErrorDTO errorReturned = this.objectMapper.readValue(stringResponse, new TypeReference<com.superhero.models.ErrorDTO>() {
        });

        Assertions.assertEquals(404, errorReturned.getCode());
        Assertions.assertEquals("Could not find the superhero", errorReturned.getDescriptionError());
    }
}
