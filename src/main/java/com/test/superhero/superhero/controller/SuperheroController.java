package com.test.superhero.superhero.controller;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.config.annotations.LogExecutionTime;
import com.test.superhero.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
public class SuperheroController implements com.superhero.api.SuperheroApi {

    @Autowired
    SuperheroService superheroService;

    @Override
    public ResponseEntity<Void> deleteSuperheroFromId(Long id) {
        this.superheroService.removeSuperheroById(id);
        return null;
    }

    @Override
    public ResponseEntity<SuperheroDTO> getSuperheroFromId(Long id) {
        return ResponseEntity.ok(superheroService.getSuperheroById(id));
    }

    @Override
    public ResponseEntity<SuperheroDTO> modifySuperhero(SuperheroDTO body) {
        return ResponseEntity.ok(superheroService.updateSuperhero(body));
    }

}
