package de.hdi.cdct.book;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

public abstract class BaseTest {
  private BookService mockedBookService = new BookService();

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.standaloneSetup(new BookController(mockedBookService));
  }
}
