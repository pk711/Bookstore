package frog.task.service;

import java.util.List;
import org.springframework.stereotype.Service;
import frog.task.model.Book;
import frog.task.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create a new book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Read a book by its ID, returns null if not found
    public Book readBook(Long id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    // Read all books
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    // Update an existing book
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // Delete a book by its ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
