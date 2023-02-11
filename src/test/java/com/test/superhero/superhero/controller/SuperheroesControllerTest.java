package com.test.superhero.superhero.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.service.SuperheroService;
import com.test.superhero.superhero.util.SuperheroTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SuperheroesControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    SuperheroService superheroService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllSuperheroes200OKTest() throws Exception {
        List<com.superhero.models.SuperheroDTO> superHeroTestList = SuperheroTestUtil.superheroesTestList();

        Mockito.when(superheroService.getAllSuperheroes()).thenReturn(superHeroTestList);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superheroes")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        ).andReturn();

        final String stringResponse = result.getResponse().getContentAsString();

        final List<SuperheroDTO> superheroDTOS = this.objectMapper.readValue(stringResponse, new TypeReference<List<SuperheroDTO>>() {
        });

        Assertions.assertEquals(superHeroTestList.size(), superheroDTOS.size());

        Assertions.assertEquals(superHeroTestList.get(0).getId(), superheroDTOS.get(0).getId());
        Assertions.assertEquals(superHeroTestList.get(0).getName(), superheroDTOS.get(0).getName());

        Assertions.assertEquals(superHeroTestList.get(1).getId(), superheroDTOS.get(1).getId());
        Assertions.assertEquals(superHeroTestList.get(1).getName(), superheroDTOS.get(1).getName());

        Assertions.assertEquals(superHeroTestList.get(2).getId(), superheroDTOS.get(2).getId());
        Assertions.assertEquals(superHeroTestList.get(2).getName(), superheroDTOS.get(2).getName());

    }


}
