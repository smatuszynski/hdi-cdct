package de.hdi.cdct.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	  public String saveBook(Book book) {
		  try {
			  repository.save(book);
				return book.getTitle() + " von " + book.getAutor() + " wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt";	  
		} catch (Exception e) {
			throw e;
		}
		 
	}
}
