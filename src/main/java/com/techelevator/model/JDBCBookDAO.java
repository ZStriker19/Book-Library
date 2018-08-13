package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCBookDAO implements BookDAO{

	
	public void saveBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public List<Book> searchForBooksInAllTables(String queryString) {
		List<Book> booksBasedOnAuthor = searchForBooksBasedOnAuthor(queryString);
		List<Book> booksBasedOnKeyword = searchForBooksBasedOnKeyword(queryString);
		List<Book> booksBasedOnPublishingLocation = searchForBooksBasedOnPublishingLocation(queryString);
		List<Book> booksBasedOnCharacter = searchForBooksBasedOnCharacter(queryString);
		List<Book> booksBasedOnTitle = searchForBooksBasedOnTitle(queryString);
	
		return null;
	}

	
	public List<Book> searchForBooksBasedOnAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Book> searchForBooksBasedOnKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Book> searchForBooksBasedOnPublishingLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Book> searchForBooksBasedOnCharacter(String character) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Book> searchForBooksBasedOnTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Book mapBookToSqlRowSet(SqlRowSet  sqlRowSet) {
		Book book = new Book();
		return book;
	}

}
