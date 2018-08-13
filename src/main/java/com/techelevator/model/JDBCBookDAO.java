package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCBookDAO implements BookDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBookDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
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
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForAuthor = "SELECT book.book_id, book.title, author.f_name, author.l_name, character.f_name, character.l_name, location.city, location.country, keyword.word" 
				+ " FROM author "
				+ " JOIN book_author ON book_author.author_id = author.author_id"
				+ " JOIN book ON book.book_id = book_author.book_id" 
				+ " JOIN book_character ON book.book_id = book_character.book_id"
				+ " JOIN character ON book_character.character_id = character.character_id"
				+ " JOIN book_location ON book.book_id = book_location.book_id"
				+ " JOIN location ON location.location_id = book_location.location_id"
				+ " JOIN book_keyword ON book.book_id = book_keyword.book_id"
				+ " JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id"   
				+ " WHERE author.f_name = 'j.k.' OR author.l_name = 'fitzgerald' ";
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForAuthor);
		if(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
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
	
	private List<Book> mapBookToSqlRowSet(SqlRowSet sqlRowSet, List<Book> books) {
		long book_id = sqlRowSet.getLong("book_id");
		for (int j = 0; j < books.size(); j++) {
			if (book_id == books.get(0).getBookId()) {
				if (!books.get(0).getAuthorFirstNames().contains(sqlRowSet.getString("author_first_name")) && !books.get(j).getAuthorLastNames().contains(sqlRowSet.getString("author_last_name"))) {
					
					
				}
			}
		}
		Book book = new Book();
		
		return books;
	}
	
	

}
