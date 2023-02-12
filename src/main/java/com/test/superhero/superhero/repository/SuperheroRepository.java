package com.test.superhero.superhero.repository;

import com.test.superhero.superhero.api.SuperheroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<SuperheroEntity, Long> {

    List<SuperheroEntity> findByNameContainingIgnoreCase(String name);
}
