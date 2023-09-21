package de.hdi.cdct.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookServiceTest {
  BookService bookService;

  @BeforeEach
  void setup() {
    bookService = new BookService();
  }

  @Test
  void addBook() {
    // GIVEN
    Book newBook = new Book();
    newBook.setIsbn("4711");
    newBook.setTitle("Ein Titel");
    newBook.setGenre("Ein Genre");

    // WHEN
    Book actualBook = bookService.addBook(newBook);

    // THEN
    assertEquals(newBook, actualBook);
    assertEquals(newBook, bookService.get("4711"));
  }

  @Test
  void addBook_no_title() {
    // GIVEN
    Book newBook = new Book();
    newBook.setIsbn("4711");
    newBook.setGenre("Ein Genre");

    // WHEN / THEN
    assertThrows(IllegalArgumentException.class, () -> bookService.addBook(newBook));
  }

  @Test
  void addBook_no_genre() {
    // GIVEN
    Book newBook = new Book();
    newBook.setIsbn("4711");
    newBook.setTitle("Ein Titel");

    // WHEN / THEN
    assertThrows(IllegalArgumentException.class, () -> bookService.addBook(newBook));
  }

  @Test
  void addBook_no_isbn() {
    // GIVEN
    Book newBook = new Book();
    newBook.setTitle("Ein Titel");
    newBook.setGenre("Ein Genre");

    // WHEN / THEN
    assertThrows(IllegalArgumentException.class, () -> bookService.addBook(newBook));
  }
}
