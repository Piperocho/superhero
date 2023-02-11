package com.test.superhero.superhero.service.impl;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.api.SuperheroEntity;
import com.test.superhero.superhero.mapper.SuperheroMapper;
import com.test.superhero.superhero.repository.SuperheroRepository;
import com.test.superhero.superhero.service.SuperheroService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SuperheroServiceImpl implements SuperheroService {

    private final SuperheroRepository superheroRepository;
    private final SuperheroMapper superheroMapper;

    public SuperheroServiceImpl(SuperheroRepository superheroRepository, SuperheroMapper superheroMapper) {
        this.superheroRepository = superheroRepository;
        this.superheroMapper = superheroMapper;
    }

    @Override
    @Transactional
    public List<SuperheroDTO> getAllSuperheroes() {

        List<SuperheroEntity> superheroEntities = this.superheroRepository.findAll();
        return superheroMapper.asDTOs(superheroEntities);
    }

    @Override
    public SuperheroDTO getSuperHeroById(Long id) {
        return null;
    }
}
