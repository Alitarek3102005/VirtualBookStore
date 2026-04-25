package com.example.catalogservice.service;

import com.example.catalogservice.DTO.InsertUpdateCategory;
import com.example.catalogservice.entity.Category;
import com.example.catalogservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
    public Category findCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public Category Save(InsertUpdateCategory category){
        Category entity = new Category();
        entity.setName(category.name());
        return categoryRepository.save(entity);
    }
    public Category UpdateCategory(Long id,InsertUpdateCategory category){
        Category entity=categoryRepository.findById(id).orElse(null);
        if(entity!=null){
            entity.setName(category.name());
            return categoryRepository.save(entity);
        }
        return null;
    }
    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }

}
