package com.techelevator.model;

import java.util.List;

public interface BookDAO {

	public void saveBook(Book book);

	public List<Book> searchForBooks(String queryString);
	
	public List<Book> searchForBooksBasedOnAuthor(String author);
	
	public List<Book> searchForBooksBasedOnGenre(String genre);
	
	public List<Book> searchForBooksBasedOnPublishingLocation(String location);
	
	public List<Book> searchForBooksBasedOnCharacter(String character);
	
	public List<Book> searchForBooksBasedOnTitle(String title);
	
	
	
}
