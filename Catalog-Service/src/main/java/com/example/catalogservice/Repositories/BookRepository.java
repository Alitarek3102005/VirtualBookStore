package com.example.catalogservice.Repositories;

import com.example.catalogservice.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByCategoryId(Long category_id);
    long countByCategoryId(Long category_id);
}
