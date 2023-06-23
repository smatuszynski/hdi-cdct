package de.hdi.cdct.book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

	@Id
	private String isbn;
	@NotNull(message="Der Titel des Buches darf nicht null sein")
	private String title;
	@NotNull(message="Der Name des Autors darf nicht null sein")
	private String autor;
	@NotNull(message="Der Erscheinungsort des Buches darf nicht null sein")
	private String erscheinungsort;
	@NotNull(message="Das Erscheinungsjahr des Buches darf nicht Null sein")
	private String erscheinungsjahr;
	@NotNull(message="Die Sprache des Buches darf nicht null sein")
	private String sprache;
	@NotNull(message="Die Laenge des Buches darf nicht Null sein")
	private Long laenge;
	
	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	private String additional;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getErscheinungsort() {
		return erscheinungsort;
	}

	public void setErscheinungsort(String erscheinungsort) {
		this.erscheinungsort = erscheinungsort;
	}

	public String getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public void setErscheinungsjahr(String erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}

	public String getSprache() {
		return sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public Long getLaenge() {
		return laenge;
	}

	public void setLaenge(Long laenge) {
		this.laenge = laenge;
	}

	public Book() {
		super();
	}

	public Book(String isbn, String title, String autor, String erscheinungsort, String erscheinungsjahr,
			String sprache, Long laenge, String additional) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.autor = autor;
		this.erscheinungsort = erscheinungsort;
		this.erscheinungsjahr = erscheinungsjahr;
		this.sprache = sprache;
		this.laenge = laenge;
		this.additional = additional;
	}

}
