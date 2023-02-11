package com.test.superhero.superhero.util;

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
}
