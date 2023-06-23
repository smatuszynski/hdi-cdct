package de.hdi.cdct.book.unitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;


import de.hdi.cdct.book.Book;
import de.hdi.cdct.book.BookController;
import de.hdi.cdct.book.BookService;


@SpringBootTest
class TestBookController {

	@MockBean
	private BookService mockedBookService;
	
	@Autowired
	private BookController booController;
	/*
	 * Es muss geprüft werden, dass ein erfolgreicher Vorgang „Buch Anlegen“ mit dem
	 * richtigen „Create_Book_Success_String“ bestätigt wird.
	 */
	@Test
	void saveBook_OK_Test() {
		Book book = new Book("1234567891232", "title", "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		when(mockedBookService.saveBook(book)).thenReturn(
				book.getTitle() + " von " + book.getAutor() + " wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt");
		ResponseEntity<String> response = booController.saveBook(book);
		assertThat(response.getStatusCode().is2xxSuccessful());
		assertThat(response.getBody()).isEqualTo(
				"title von autor wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt");
	}
	/*
	 * Es muss geprüft werden, ob Anlagen eines Buches mit fehlenden erforderlichen
	 * Buchinformationen nicht akzeptiert werden und zu einer Exception (+ fachliche
	 * Fehlermeldung) führen.
	 */
	@Test
	void SaveBook_null_Validation_Exception_Test() {
		Book book = new Book("1234567891231", null, "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		when(mockedBookService.saveBook(book)).thenThrow(new TransactionSystemException(null,
				new Throwable(new Throwable("Der Titel des Buches darf nicht null sein"))));
		ResponseEntity<String> response = booController.saveBook(book);
		assertThat(response.getStatusCode().is4xxClientError());
		assertThat(response.getBody()).contains("Der Titel des Buches darf nicht null sein");
	}
	/*
	 * Es muss geprüft werden, ob die fachlich nicht validierten Eingabefelder nicht
	 * akzeptiert werden.
	 */
}
