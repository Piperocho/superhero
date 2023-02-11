package com.test.superhero.superhero.service;

import com.test.superhero.superhero.service.impl.SuperheroServiceImpl;
import com.test.superhero.superhero.util.SuperheroTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class SuperheroServiceImplTest {

    @InjectMocks
    SuperheroServiceImpl superheroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllSuperheroesTest() {
        List<com.superhero.models.SuperheroDTO> superHeroTestList = SuperheroTestUtil.superheroesTestList();

        List<com.superhero.models.SuperheroDTO> superheroDTOS = this.superheroService.getAllSuperheroes();

        Assertions.assertEquals(superHeroTestList.size(), superheroDTOS.size());

        Assertions.assertEquals(superHeroTestList.get(0).getId(), superheroDTOS.get(0).getId());
        Assertions.assertEquals(superHeroTestList.get(0).getId(), superheroDTOS.get(0).getId());

        Assertions.assertEquals(superHeroTestList.get(1).getId(), superheroDTOS.get(1).getId());
        Assertions.assertEquals(superHeroTestList.get(1).getId(), superheroDTOS.get(1).getId());

        Assertions.assertEquals(superHeroTestList.get(2).getId(), superheroDTOS.get(2).getId());
        Assertions.assertEquals(superHeroTestList.get(2).getId(), superheroDTOS.get(2).getId());

    }
}
