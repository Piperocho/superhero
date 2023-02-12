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

    /**
     * Gets superheroes by name.
     *
     * @return the superheroes by name
     */
    List<com.superhero.models.SuperheroDTO> getSuperheroesByName(String name);

    /**
     * Gets super hero by id.
     *
     * @param id the id
     * @return the super hero by id
     */
    com.superhero.models.SuperheroDTO getSuperheroById(Long id);


    /**
     * Update superhero com . superhero . models . superhero dto.
     *
     * @param body the body
     * @return the com . superhero . models . superhero dto
     */
    com.superhero.models.SuperheroDTO updateSuperhero(com.superhero.models.SuperheroDTO body);
}
