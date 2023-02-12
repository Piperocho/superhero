package com.test.superhero.superhero.service;

import com.test.superhero.superhero.api.Exception.EntityNotFoundException;
import com.test.superhero.superhero.api.Exception.InputValidationException;
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
        Assertions.assertEquals(superHeroTestList.get(0).getName(), superheroDTOS.get(0).getName());

        Assertions.assertEquals(superHeroTestList.get(1).getId(), superheroDTOS.get(1).getId());
        Assertions.assertEquals(superHeroTestList.get(1).getName(), superheroDTOS.get(1).getName());

        Assertions.assertEquals(superHeroTestList.get(2).getId(), superheroDTOS.get(2).getId());
        Assertions.assertEquals(superHeroTestList.get(2).getName(), superheroDTOS.get(2).getName());

    }

    @Test
    public void updateSuperheroOkTest() {

        com.superhero.models.SuperheroDTO superheroExpected = new com.superhero.models.SuperheroDTO();
        superheroExpected.setId(1l);
        superheroExpected.setName("Superwoman");

        com.superhero.models.SuperheroDTO superheroReturned = this.superheroService.updateSuperhero(superheroExpected);

        Assertions.assertEquals(superheroExpected.getId(), superheroReturned.getId());
        Assertions.assertEquals(superheroExpected.getName(), superheroReturned.getName());

    }

    @Test
    public void updateSuperheroNotFoundKOTest() {

        com.superhero.models.SuperheroDTO superheroExpected = new com.superhero.models.SuperheroDTO();
        superheroExpected.setId(10l);
        superheroExpected.setName("Superwoman");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.superheroService.updateSuperhero(superheroExpected);
        });

    }

    @Test
    public void updateSuperheroInputExceptionKOTest() {

        com.superhero.models.SuperheroDTO superheroExpected = new com.superhero.models.SuperheroDTO();
        superheroExpected.setId(-1l);
        superheroExpected.setName("Superwoman");
        Assertions.assertThrows(InputValidationException.class, () -> {
            this.superheroService.updateSuperhero(superheroExpected);
        });

    }


}
