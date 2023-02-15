package frog.task.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import frog.task.model.Book;
import frog.task.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    // Inject BookService to handle requests
    @Autowired
    BookService bookService;

    // GET request to read all books
    @GetMapping
    public List<Book> readAllBooks() {
        return bookService.readAllBooks();
    }

    // GET request to read a book by ID
    @GetMapping("/{id}")
    public Book readBookById(@PathVariable("id") Long id) {
        return bookService.readBook(id);
    }

    // POST request to create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // PUT request to update an existing book by ID
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    // DELETE request to delete a book by ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
