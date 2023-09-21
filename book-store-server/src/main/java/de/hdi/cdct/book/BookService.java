package de.hdi.cdct.book;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
  private final Map<String, Book> books = Collections.synchronizedMap(new HashMap<>());

  public BookService() {
      Book book = new Book();
      book.setGenre("Technology");
      book.setTitle("Java 11");
      book.setIsbn("42");
      books.put(book.getIsbn(), book);
  }

  public Book get(String isbn) {
    return books.get(isbn);
  }

  public List<Book> getBooks() {
    return new ArrayList<>(books.values());
  }

  public Book addBook(Book newBook) {
    if (newBook == null) {
      throw new IllegalArgumentException("Parameter darf nicht null sein.");
    }
    if (newBook.getIsbn() == null || newBook.getIsbn().isEmpty()) {
      throw new IllegalArgumentException("ISBN darf nicht leer sein.");
    }
    if (newBook.getTitle() == null || newBook.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Titel darf nicht leer sein.");
    }
    if (newBook.getGenre() == null || newBook.getGenre().isEmpty()) {
      throw new IllegalArgumentException("Genre darf nicht leer sein.");
    }

    books.put(newBook.getIsbn(), newBook);
    return newBook;
  }
}
