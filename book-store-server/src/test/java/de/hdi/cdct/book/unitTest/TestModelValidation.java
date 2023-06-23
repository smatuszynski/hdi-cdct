package de.hdi.cdct.book.unitTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import de.hdi.cdct.book.Book;
import de.hdi.cdct.book.BookRepository;

@SpringBootTest
class TestModelValidation {

	@Autowired
	private BookRepository bookRepository;

	
	/*
	 * Prüfung des erfolgreichen Prozesses „Buch Anlegen"
	 */
	
	@Test
	void creatBook_OK_Test() {
		Book book = new Book("1234567891232", "title", "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		bookRepository.save(book);
		Optional<Book> bookRp = bookRepository.findById("1234567891232");
		
		assertThat(book.getAutor()).isEqualTo(bookRp.get().getAutor());
		assertThat(book.getTitle()).isEqualTo(bookRp.get().getTitle());
	}
	
	/* Es muss geprüft werden, ob Anlagen eines Buches mit fehlenden erforderlichen
	 * Buchinformationen nicht akzeptiert werden und zu einer Exception (+ fachliche
	 * Fehlermeldung) führen.
	 */
	@Test
	void SaveBook_null_Validation_Exception_Test() {
		Book book = new Book("1234567891231", null, "autor", "erscheinungsort", "erscheinungsjahr", "sprache", 233L,
				"additional");
		try {
			bookRepository.save(book);
	} catch (Exception e) {
		assertThat(e.getCause().getCause().getLocalizedMessage()).contains(
				"Der Titel des Buches darf nicht null sein");
	}
	}
	
	/*
	 * Es muss geprüft werden, ob die fachlich nicht validierten Eingabefelder nicht
	 * akzeptiert werden.
	 */
}
