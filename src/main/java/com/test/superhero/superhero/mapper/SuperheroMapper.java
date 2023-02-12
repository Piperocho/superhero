package com.test.superhero.superhero.mapper;

import com.test.superhero.superhero.api.SuperheroEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SuperheroMapper {

    List<com.superhero.models.SuperheroDTO> asDTOs(List<SuperheroEntity> entities);

    com.superhero.models.SuperheroDTO asDTO(SuperheroEntity entities);

}
