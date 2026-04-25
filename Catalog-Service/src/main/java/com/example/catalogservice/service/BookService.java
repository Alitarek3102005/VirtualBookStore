package com.example.catalogservice.service;

import com.example.catalogservice.DTO.BookResponse;
import com.example.catalogservice.DTO.InsertBook;
import com.example.catalogservice.DTO.UpdateBook;
import com.example.catalogservice.client.AuthServiceClient;
import com.example.catalogservice.client.UserDto;
import com.example.catalogservice.entity.Book;
import com.example.catalogservice.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthServiceClient authServiceClient;


    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }


    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return mapToBookResponse(book);
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
        Book bookEntity = bookRepository.findById(id).orElse(null);
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
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return book.getQuantity();
        }
        return null;
    }
    public void decreaseBookQuantity(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }
    public void  increaseBookQuantity(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
    public void addBookQuantity(Long id, int quantity) {
        Book book =bookRepository.findById(id).orElse(null);
        book.setQuantity(book.getQuantity() + quantity);
        bookRepository.save(book);
    }
    @Transactional
    public void reduceBookQuantity(Long bookId, Long quantityToReduce) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() < quantityToReduce) {
            throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
        }
        book.setQuantity(book.getQuantity() - quantityToReduce);
        bookRepository.save(book);
    }
    public boolean checkStockAvailability(Long bookId, Long requestedQuantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return book.getQuantity() >= requestedQuantity;
    }
    private BookResponse mapToBookResponse(Book book) {
        String publisherName = "Unknown Publisher";
        Long publisherId = book.getPublisher_id();
        try {
            if (book.getPublisher_id() != null) {
                UserDto publisher = authServiceClient.getPublisherById(book.getPublisher_id());
                if (publisher != null && publisher.getUsername() != null) {
                    publisherName = publisher.getUsername();
                    publisherId=publisher.getId();
                }
            }
        } catch (Exception e) {
            System.out.println("Feign Error fetching publisher: " + e.getMessage());
        }
        return BookResponse.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .price(book.getPrice())
            .description(book.getDescription())
            .imageUrl(book.getImageUrl())
            .stockQuantity(book.getQuantity())
            .quantity(book.getQuantity())
            .categoryName(book.getCategory().getName())
            .publisherName(publisherName)
            .build();
    }
}
