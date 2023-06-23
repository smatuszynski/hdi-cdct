package de.hdi.cdct.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/saveBook")
	public ResponseEntity<String> saveBook(@RequestBody Book book) {
		try {
			return ResponseEntity.ok(bookService.saveBook(book));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());
		}	
	}
	
}
