package com.api.Restwithspringbootandjava.repositories;

import com.api.Restwithspringbootandjava.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel,Long> {}
