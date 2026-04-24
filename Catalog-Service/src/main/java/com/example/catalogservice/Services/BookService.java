package com.example.catalogservice.Services;

import com.example.catalogservice.DTOs.InsertBook;
import com.example.catalogservice.DTOs.UpdateBook;
import com.example.catalogservice.Entities.Book;
import com.example.catalogservice.Repositories.BookRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book save(InsertBook book) {
        Book bookEntity = new Book();
        bookEntity.setTitle(book.title());
        bookEntity.setAuthor(book.author());
        bookEntity.setCategory(book.category());
        bookEntity.setPrice(book.price());
        bookEntity.setPublisher_id(book.publisher_id());
        bookEntity.setQuantity(book.quantity());
        return bookRepository.save(bookEntity);
    }
    public Book update(Long id, UpdateBook book) {
        Book bookEntity = findById(id);
        if (bookEntity != null) {
            bookEntity.setPrice(book.price());
            bookEntity.setCategory(book.category());
            return bookRepository.save(bookEntity);
        }
        return null;
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    public List<Book> findByCategoryId(Long category_id) {
        return bookRepository.findByCategoryId(category_id);
    }
    public List<Book> findSpecificBooks(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }
    public Long count() {
        return bookRepository.count();
    }
    public Long countByCategoryId(Long category_id) {
        return bookRepository.countByCategoryId(category_id);
    }
    public Long getBookQuantity(Long id) {
        Book book = findById(id);
        return book.getQuantity();
    }
    public void decreaseBookQuantity(Long id) {
        Book book = findById(id);
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }
    public void  increaseBookQuantity(Long id) {
        Book book = findById(id);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
    public void addBookQuantity(Long id, int quantity) {
        Book book = findById(id);
        book.setQuantity(book.getQuantity() + quantity);
        bookRepository.save(book);
    }
    public void reduceBookQuantity(Long id, Long quantity) {
        Book book = findById(id);
        book.setQuantity(book.getQuantity() - quantity);
        bookRepository.save(book);
    }

}
