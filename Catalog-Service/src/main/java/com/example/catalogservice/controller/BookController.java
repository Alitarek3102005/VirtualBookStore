package com.example.catalogservice.controller;

import com.example.catalogservice.DTO.BookResponse;
import com.example.catalogservice.DTO.InsertBook;
import com.example.catalogservice.DTO.UpdateBook;
import com.example.catalogservice.entity.Book;
import com.example.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
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
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'PUBLISHER')")
    public Book create(@RequestBody @Valid InsertBook book){
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public Book update(@PathVariable  Long id, @RequestBody @Valid UpdateBook book){
        return bookService.update(id,book);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        bookService.deleteById(id);
    }

    @GetMapping("/{id}/check-stock")
    public ResponseEntity<Boolean> checkStock(
            @PathVariable Long id,
            @RequestParam Long quantity) {

        boolean isAvailable = bookService.checkStockAvailability(id, quantity);
        return ResponseEntity.ok(isAvailable);
    }

    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<String> reduceStock(
            @PathVariable Long id,
            @RequestParam Long quantity) {
        bookService.reduceBookQuantity(id, quantity);
        return ResponseEntity.ok("Stock reduced successfully");
    }
    @GetMapping("/lowstock")
    public List<BookResponse> getLowStockBooks(){
        return bookService.GetLowStockBooks();
    }
    @PutMapping("/{id}/updatequantity")
    public BookResponse updateQuantity(@PathVariable Long id,@RequestParam Long quantity){
        return bookService.updateQuantity(id, quantity);
    }

}
