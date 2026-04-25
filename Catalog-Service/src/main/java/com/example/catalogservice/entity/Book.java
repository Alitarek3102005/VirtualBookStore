package com.example.catalogservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private Double price;
    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    private String description;
    private String imageUrl;
    @NotNull
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(name = "publisher_id",nullable = false)
    private Long publisher_id;
}
