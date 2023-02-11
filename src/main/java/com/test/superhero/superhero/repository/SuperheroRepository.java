package com.test.superhero.superhero.repository;

import com.test.superhero.superhero.api.SuperheroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends JpaRepository<SuperheroEntity, Long> {
}
