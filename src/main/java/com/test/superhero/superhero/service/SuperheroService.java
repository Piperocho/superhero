package com.test.superhero.superhero.service;


import java.util.List;

/**
 * The interface Superhero service.
 */
public interface SuperheroService {

    /**
     * Gets all superheroes.
     *
     * @return the all superheroes
     */
    List<com.superhero.models.SuperheroDTO> getAllSuperheroes();
}
