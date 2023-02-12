package com.test.superhero.superhero.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.service.SuperheroService;
import com.test.superhero.superhero.util.SuperheroTestUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
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

    public static List<com.superhero.models.SuperheroDTO> superheroesByNameTestList() {
        List<com.superhero.models.SuperheroDTO> superheroDTOS = new ArrayList<>();

        com.superhero.models.SuperheroDTO superheroDTO1 = new com.superhero.models.SuperheroDTO();
        superheroDTO1.setId(1l);
        superheroDTO1.setName("Superman");
        superheroDTOS.add(superheroDTO1);

        com.superhero.models.SuperheroDTO superheroDTO2 = new com.superhero.models.SuperheroDTO();
        superheroDTO2.setId(2l);
        superheroDTO2.setName("Manolito el fuerte");
        superheroDTOS.add(superheroDTO2);

        return superheroDTOS;

    }

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

    @Test
    public void getSuperheroesByName200OKFoundResultsTest() throws Exception {
        List<com.superhero.models.SuperheroDTO> superHeroTestList = this.superheroesByNameTestList();

        Mockito.when(this.superheroService.getSuperheroesByName("man")).thenReturn(superHeroTestList);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superheroes/findByName?name=man")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        ).andReturn();

        final String stringResponse = result.getResponse().getContentAsString();

        final List<SuperheroDTO> superheroDTOS = this.objectMapper.readValue(stringResponse, new TypeReference<List<SuperheroDTO>>() {
        });

        Assertions.assertEquals(2, superheroDTOS.size());

        superheroDTOS.forEach(superheroDTO -> Assertions.assertTrue(StringUtils.containsIgnoreCase(superheroDTO.getName(), ("man"))));

    }

    @Test
    public void getSuperheroesByName200OKNotFoundResultsTest() throws Exception {

        Mockito.when(this.superheroService.getSuperheroesByName("evil")).thenReturn(Collections.emptyList());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superheroes/findByName?name=evil")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isEmpty()
        ).andReturn();

    }


}
