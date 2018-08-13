package com.techelevator.model;

import java.util.List;

public interface BookDAO {

	public void saveBook(Book book);

	public List<Book> searchForBookInAllTables(String queryString);
	
	public List<Book> searchForBookBasedOnAuthor(String author);
	
	public List<Book> searchForBookBasedOnKeyword(String keyword);
	
	public List<Book> searchForBookBasedOnPublishingLocation(String location);
	
	public List<Book> searchForBookBasedOnCharacter(String character);
	
	public List<Book> searchForBookBasedOnTitle(String title);
	
	
	
}
