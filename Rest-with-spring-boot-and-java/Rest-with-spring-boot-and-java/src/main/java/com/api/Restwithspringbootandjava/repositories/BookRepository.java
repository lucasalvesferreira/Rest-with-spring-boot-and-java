package com.api.Restwithspringbootandjava.repositories;

import com.api.Restwithspringbootandjava.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {}
