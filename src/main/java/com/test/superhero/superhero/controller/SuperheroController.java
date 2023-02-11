package com.test.superhero.superhero.controller;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperheroController implements com.superhero.api.SuperheroApi {

    @Autowired
    SuperheroService superheroService;

    @Override
    public ResponseEntity<SuperheroDTO> getSuperheroFromId(Long id) {
        return ResponseEntity.ok(superheroService.getSuperheroById(id));
    }
}
