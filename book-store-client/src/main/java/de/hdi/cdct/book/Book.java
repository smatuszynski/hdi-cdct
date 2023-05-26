package de.hdi.cdct.book;

public class Book {

  private String title;
  private String genre;
  private String isbn;

  public String getAdditional() {
    return additional;
  }

  public void setAdditional(String additional) {
    this.additional = additional;
  }

  private String additional;

  public Book(String title, String genre, String isbn) {
    this.title = title;
    this.genre = genre;
    this.isbn = isbn;
  }

  public Book() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
}
