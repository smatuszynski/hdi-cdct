package de.hdi.cdct.book;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

  public List<Book> getBooks() {
    return new ArrayList<>(books.values());
  }
}
