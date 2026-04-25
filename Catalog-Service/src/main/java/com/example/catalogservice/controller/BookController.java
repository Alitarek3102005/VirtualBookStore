package com.example.catalogservice.controller;

import com.example.catalogservice.DTO.InsertBook;
import com.example.catalogservice.DTO.UpdateBook;
import com.example.catalogservice.entity.Book;
import com.example.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping()
    public List<Book> findAll(){
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return bookService.findById(id);
    }
    @GetMapping("/category/{category_id}")
    public List<Book> findByCategory(@PathVariable Long category_id){
        return  bookService.findByCategoryId(category_id);
    }
    @GetMapping("/batch")
    public List<Book> findSpecificBooks(@RequestParam List<Long> ids){
        return bookService.findSpecificBooks(ids);
    }
    @GetMapping("/{id}/availability")
    public Long findBookAvailabilityById(@PathVariable Long id){
        return bookService.getBookQuantity(id);
    }
    @PostMapping
    public Book create(@RequestBody @Valid InsertBook book){
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public Book update(@PathVariable  Long id, @RequestBody @Valid UpdateBook book){
        return bookService.update(id,book);
    }
    @PutMapping("/{id}/reduce-stock")
    public void reduceStock(@PathVariable Long id,Long quantity){
        bookService.reduceBookQuantity(id,quantity);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        bookService.deleteById(id);
    }


}
