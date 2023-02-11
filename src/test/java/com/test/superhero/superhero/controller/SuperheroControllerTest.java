package com.test.superhero.superhero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.models.SuperheroDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SuperheroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<SuperheroDTO> superheroesTestList() {
        List<com.superhero.models.SuperheroDTO> superheroDTOS = new ArrayList<>();

        com.superhero.models.SuperheroDTO superheroDTO1 = new SuperheroDTO();
        superheroDTO1.setId(1l);
        superheroDTO1.setName("Superman");
        superheroDTOS.add(superheroDTO1);

        com.superhero.models.SuperheroDTO superheroDTO2 = new SuperheroDTO();
        superheroDTO2.setId(2l);
        superheroDTO2.setName("Spiderman");
        superheroDTOS.add(superheroDTO2);

        com.superhero.models.SuperheroDTO superheroDTO3 = new SuperheroDTO();
        superheroDTO3.setId(3l);
        superheroDTO3.setName("Dr. Strange");
        superheroDTOS.add(superheroDTO3);

        return superheroDTOS;

    }
    @Test
    public void getAllSuperheroes200OKTest() throws Exception {

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/superheroes")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        ).andReturn();

        final String stringResponse = result.getResponse().getContentAsString();

        final List<SuperheroDTO> superheroDTOS = this.objectMapper.readValue(stringResponse, List.class);

        Assertions.assertEquals(this.superheroesTestList().size(), superheroDTOS.size());

        Assertions.assertEquals(this.superheroesTestList().get(0).getId(), superheroDTOS.get(0).getId());
        Assertions.assertEquals(this.superheroesTestList().get(0).getId(), superheroDTOS.get(0).getId());

        Assertions.assertEquals(this.superheroesTestList().get(1).getId(), superheroDTOS.get(1).getId());
        Assertions.assertEquals(this.superheroesTestList().get(1).getId(), superheroDTOS.get(1).getId());

        Assertions.assertEquals(this.superheroesTestList().get(2).getId(), superheroDTOS.get(2).getId());
        Assertions.assertEquals(this.superheroesTestList().get(2).getId(), superheroDTOS.get(2).getId());

    }

}
