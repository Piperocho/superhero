package com.test.superhero.superhero.util;

import com.test.superhero.superhero.api.SuperheroEntity;

import java.util.ArrayList;
import java.util.List;

public class SuperheroTestUtil {

    public static List<com.superhero.models.SuperheroDTO> superheroesTestList() {
        List<com.superhero.models.SuperheroDTO> superheroDTOS = new ArrayList<>();

        com.superhero.models.SuperheroDTO superheroDTO1 = new com.superhero.models.SuperheroDTO();
        superheroDTO1.setId(1l);
        superheroDTO1.setName("Superman");
        superheroDTOS.add(superheroDTO1);

        com.superhero.models.SuperheroDTO superheroDTO2 = new com.superhero.models.SuperheroDTO();
        superheroDTO2.setId(2l);
        superheroDTO2.setName("Spiderman");
        superheroDTOS.add(superheroDTO2);

        com.superhero.models.SuperheroDTO superheroDTO3 = new com.superhero.models.SuperheroDTO();
        superheroDTO3.setId(3l);
        superheroDTO3.setName("Dr. Strange");
        superheroDTOS.add(superheroDTO3);

        return superheroDTOS;

    }

    public static List<SuperheroEntity> superheroesEntitiesTestList() {
        List<SuperheroEntity> superheroEntities = new ArrayList<>();

        SuperheroEntity superheroEntity1 = new SuperheroEntity();
        superheroEntity1.setId(1l);
        superheroEntity1.setName("Superman");
        superheroEntities.add(superheroEntity1);

        SuperheroEntity superheroEntity2 = new SuperheroEntity();
        superheroEntity2.setId(2l);
        superheroEntity2.setName("Spiderman");
        superheroEntities.add(superheroEntity2);

        SuperheroEntity superheroEntity3 = new SuperheroEntity();
        superheroEntity3.setId(3l);
        superheroEntity3.setName("Dr. Strange");
        superheroEntities.add(superheroEntity3);

        return superheroEntities;

    }
}
