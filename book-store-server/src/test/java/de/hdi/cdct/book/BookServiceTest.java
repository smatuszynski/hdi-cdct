package de.hdi.cdct.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {
  BookService bookService;

  @BeforeEach
  void setup() {
    bookService = new BookService();
  }
}
