package com.test.superhero.superhero.service.impl;

import com.superhero.models.SuperheroDTO;
import com.test.superhero.superhero.api.Exception.EntityNotFoundException;
import com.test.superhero.superhero.api.Exception.InputValidationException;
import com.test.superhero.superhero.api.SuperheroEntity;
import com.test.superhero.superhero.mapper.SuperheroMapper;
import com.test.superhero.superhero.repository.SuperheroRepository;
import com.test.superhero.superhero.service.SuperheroService;
import com.test.superhero.superhero.service.constant.SuperheroConstants;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public List<SuperheroDTO> getSuperheroesByName(String name) {
        List<SuperheroEntity> superheroEntities = this.superheroRepository.findByNameContainingIgnoreCase(name);
        return superheroMapper.asDTOs(superheroEntities);
    }

    @Override
    @Transactional
    public SuperheroDTO getSuperheroById(Long id) {
        Optional<SuperheroEntity> optionalSuperheroEntity = this.superheroRepository.findById(id);

        if (optionalSuperheroEntity.isPresent()) {
            return superheroMapper.asDTO(optionalSuperheroEntity.get());
        } else {
            throw new EntityNotFoundException(SuperheroConstants.SUPERHERO_NOT_FOUND);
        }


    }

    @Override
    @Transactional
    public SuperheroDTO updateSuperhero(SuperheroDTO superheroDTO) {

        SuperheroEntity superheroEntity = this.validateAndReturnEntity(superheroDTO);

        superheroEntity.setName(superheroDTO.getName());

        this.superheroRepository.save(superheroEntity);

        return superheroMapper.asDTO(superheroEntity);
    }

    @Override
    @Transactional
    public void removeSuperheroById(Long id) {
        try {
            this.superheroRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            throw new EntityNotFoundException(SuperheroConstants.SUPERHERO_NOT_FOUND);
        }
    }

    private SuperheroEntity validateAndReturnEntity(SuperheroDTO superheroDTO) {
        if (superheroDTO.getId() <= 0l) {
            throw new InputValidationException(SuperheroConstants.SUPERHERO_ID_IVALID);
        }

        Optional<SuperheroEntity> optionalSuperheroEntity = this.superheroRepository.findById(superheroDTO.getId());
        if (!optionalSuperheroEntity.isPresent()) {
            throw new EntityNotFoundException(SuperheroConstants.SUPERHERO_NOT_FOUND);
        }
        return optionalSuperheroEntity.get();
    }
}
