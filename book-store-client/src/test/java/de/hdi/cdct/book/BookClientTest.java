package de.hdi.cdct.book;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureStubRunner(
  ids = {"de.hdi.cdct:book-store-server:+:stubs:8080"},
  stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class BookClientTest {

  @Autowired
  private BookClient cut;

  @Test
  void testContractToBookStoreServer() {

    JsonNode result = cut.getAllAvailableBooks();

    assertTrue(result.isArray());

    JsonNode firstBook = result.get(0);

    assertTrue(firstBook.has("genre"));
    assertTrue(firstBook.has("title"));
    assertTrue(firstBook.has("isbn"));
    assertTrue(firstBook.get("isbn").isNumber());
    assertTrue(firstBook.get("genre").isTextual());
    assertTrue(firstBook.get("title").isTextual());
  }
}
