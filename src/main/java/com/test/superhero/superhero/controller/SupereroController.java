package com.test.superhero.superhero.controller;

import com.superhero.models.SuperheroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupereroController implements com.superhero.api.SuperheroesApi {

    @Override
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes() {
        return null;
    }
}
