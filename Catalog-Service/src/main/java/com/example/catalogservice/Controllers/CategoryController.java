package com.example.catalogservice.Controllers;

import com.example.catalogservice.DTOs.InsertUpdateCategory;
import com.example.catalogservice.Entities.Category;
import com.example.catalogservice.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAllCategory();
    }
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
    }
    @PostMapping
    public Category save(@RequestBody InsertUpdateCategory category){
        return categoryService.Save(category);
    }
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody InsertUpdateCategory category){
        return categoryService.UpdateCategory(id,category);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
}

