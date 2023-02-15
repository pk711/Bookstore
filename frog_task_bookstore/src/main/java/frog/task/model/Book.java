package frog.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    // Define the entity properties
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Generates a unique value for the primary key
    private Long id;
    private String title;
    private String author;
    private String ISBN;

    // Default constructor
    public Book() {}

    // Constructor with arguments
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    // Getter and setter methods for the properties
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
