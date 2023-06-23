package de.hdi.cdct.book.unitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.TransactionSystemException;

import de.hdi.cdct.book.Book;
import de.hdi.cdct.book.BookRepository;
import de.hdi.cdct.book.BookService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestBookService {

	@MockBean
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookServiceTarget;

	/*
	 * Es muss geprüft werden, dass ein erfolgreicher Vorgang „Buch Anlegen“ mit dem
	 * richtigen „Create_Book_Success_String“ bestätigt wird.
	 */
	
	@Test
	void SaveBook_OK_Test() {
		Book book = new Book("1234567891232", "title", "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		when(bookRepository.save(book)).thenReturn(book);
		String response = bookServiceTarget.saveBook(book);
		assertThat(response).isEqualTo(
				book.getTitle() + " von " + book.getAutor() + " wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt");
	}
	
	/*
	 * Es muss geprüft werden, ob Anlagen eines Buches mit fehlenden erforderlichen
	 * Buchinformationen nicht akzeptiert werden und zu einer Exception (+ fachliche
	 * Fehlermeldung) führen.
	 */
	@Test
	void SaveBook_null_Validation_Exception_Test() {
		Book book = new Book("1234567891232", null, "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		when(bookRepository.save(book)).thenThrow(new TransactionSystemException(null,
				new Throwable(new Throwable("Der Titel des Buches darf nicht null sein"))));
	
		try {
			bookServiceTarget.saveBook(book);
		} catch (Exception e) {
			assertThat(e.getCause().getCause().getLocalizedMessage())
					.contains("Der Titel des Buches darf nicht null sein");
		}
	}
	
	/*
	 * Es muss geprüft werden, ob die fachlich nicht validierten Eingabefelder nicht
	 * akzeptiert werden.
	 */
}
