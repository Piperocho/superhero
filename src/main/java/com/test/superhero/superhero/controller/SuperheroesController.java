package com.test.superhero.superhero.controller;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.config.annotations.LogExecutionTime;
import com.test.superhero.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@LogExecutionTime
public class SuperheroesController implements com.superhero.api.SuperheroesApi {


    @Autowired
    SuperheroService superheroService;

    @Override
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes() {
        return ResponseEntity.ok(superheroService.getAllSuperheroes());
    }

    @Override
    public ResponseEntity<List<SuperheroDTO>> getSuperheroesByName(String name) {
        return ResponseEntity.ok(superheroService.getSuperheroesByName(name));
    }
}
