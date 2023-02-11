package com.test.superhero.superhero.service;

import com.test.superhero.superhero.api.SuperheroEntity;
import com.test.superhero.superhero.mapper.SuperheroMapper;
import com.test.superhero.superhero.repository.SuperheroRepository;
import com.test.superhero.superhero.service.impl.SuperheroServiceImpl;
import com.test.superhero.superhero.util.SuperheroTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class SuperheroServiceImplTest {

    @InjectMocks
    SuperheroServiceImpl superheroService;

    @Mock
    SuperheroRepository superheroRepository;

    @Mock
    SuperheroMapper superheroMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllSuperheroesTest() {

        List<SuperheroEntity> superheroEntitiesTest = SuperheroTestUtil.superheroesEntitiesTestList();

        Mockito.when(this.superheroRepository.findAll()).thenReturn(superheroEntitiesTest);

        List<com.superhero.models.SuperheroDTO> superHeroTestList = SuperheroTestUtil.superheroesTestList();

        Mockito.when(this.superheroMapper.asDTOs(superheroEntitiesTest)).thenReturn(superHeroTestList);

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
