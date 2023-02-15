package frog.task.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.Arrays;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import frog.task.model.Book;
import frog.task.service.BookService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;

    @Test
    public void testReadAllBooks() throws Exception {
        // Create a sample book
        Book book1 = new Book("Book1", "Author1","1");
        Book book2 = new Book("Book2", "Author2","2");

        // Set up the behavior of the mocked BookService instance
        when(bookService.readAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Perform a GET request to "/api/books/1" and verify the response
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Book1")))
                .andExpect(jsonPath("$[0].author", is("Author1")))
                .andExpect(jsonPath("$[0].isbn", is("1")))
                .andExpect(jsonPath("$[1].title", is("Book2")))
                .andExpect(jsonPath("$[1].author", is("Author2")))
                .andExpect(jsonPath("$[1].isbn", is("2")));
    }
    @Test
    public void testReadBookById() throws Exception {
        // Create a sample book
        Book book = new Book("Book1", "Author1","1");
        book.setId(1L);

        // Set up the behavior of the mocked BookService instance
        when(bookService.readBook(1L)).thenReturn(book);

        // Perform a GET request to "/api/books/1" and verify the response
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Book1")))
                .andExpect(jsonPath("$.author", is("Author1")))
                .andExpect(jsonPath("$.isbn", is("1")));
    }
    @Test
    public void testCreateBook() throws Exception {
        // Create a sample book
        Book book = new Book("Book1", "Author1","1");

        // Set up the behavior of the mocked BookService instance
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        // Perform a POST request to "/api/books" and verify the response
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Book1\", \"author\": \"Author1\", \"isbn\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Book1")))
                .andExpect(jsonPath("$.author", is("Author1")))
                .andExpect(jsonPath("$.isbn", is("1")));
    }
    @Test
    public void testUpdateBook() throws Exception {
        // Create a sample book
        Book book = new Book("Book1", "Author1","1");
        book.setId(1L);

        // Set up the behavior of the mocked BookService instance
        when(bookService.updateBook(any(Book.class))).thenReturn(book);

        // Perform a PUT request to "/api/books" and verify the response
        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Book1\", \"author\": \"Author1\", \"isbn\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Book1")))
                .andExpect(jsonPath("$.author", is("Author1")))
                .andExpect(jsonPath("$.isbn", is("1")));
    }
    @Test
    public void testDeleteBook() throws Exception {
        // Create a book to be deleted
        Book book = new Book("Book1", "Author1", "1");
        book.setId(1L);

        // Perform the DELETE request and verify the server response
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(status().isOk());

        // Verify that the book was deleted from the repository
        verify(bookService, times(1)).deleteBook(1L);
    }

}
