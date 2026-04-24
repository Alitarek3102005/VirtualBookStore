package com.example.catalogservice.Repositories;

import com.example.catalogservice.DTOs.InsertUpdateCategory;
import com.example.catalogservice.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}

