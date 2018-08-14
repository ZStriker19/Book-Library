package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component

public class JDBCBookDAO implements BookDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBookDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public void saveBook(Book book) {
		// TODO Auto-generated method stub	
	}

	public List<Book> searchForBooks(String queryString) {
		queryString = queryString.toLowerCase();
		
		List<Book> allBooksFromQueries = new ArrayList<Book>();
		
		allBooksFromQueries.addAll(searchForBooksBasedOnAuthor(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnKeyword(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnPublishingLocation(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnCharacter(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnTitle(queryString));
		
		List<Book> booksWithoutDuplicates = deleteDuplicateBooks(allBooksFromQueries);
		return booksWithoutDuplicates;
	}

	
	public List<Book> searchForBooksBasedOnAuthor(String author) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForAuthor = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+ " FROM author "
				+ " JOIN book_author ON book_author.author_id = author.author_id"
				+ " JOIN book ON book.book_id = book_author.book_id" 
				+ " JOIN book_character ON book.book_id = book_character.book_id"
				+ " JOIN character ON book_character.character_id = character.character_id"
				+ " JOIN book_location ON book.book_id = book_location.book_id"
				+ " JOIN location ON location.location_id = book_location.location_id"
				+ " JOIN book_keyword ON book.book_id = book_keyword.book_id"
				+ " JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id"   
				+ " WHERE author.f_name = ? OR author.l_name = ? ";
		
		SqlRowSet  results = jdbcTemplate.queryForRowSet(sqlQueryForAuthor, author, author);
		
		while(results.next()) {
			books = mapBookToSqlRowSet(results, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnKeyword(String keyword) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForKeyword = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+" FROM keyword" 
				+" JOIN book_keyword ON keyword.keyword_id = book_keyword.keyword_id" 
				+" JOIN book ON book.book_id = book_keyword.book_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" JOIN book_character ON book.book_id = book_character.book_id"
				+" JOIN character ON book_character.character_id = character.character_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" WHERE keyword.word = ?";
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForKeyword, keyword);
		while(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnPublishingLocation(String location) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForLocation = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+" FROM location"
				+" JOIN book_location ON location.location_id = book_location.location_id"
				+" JOIN book ON book.book_id = book_location.book_id" 
				+" JOIN book_keyword ON book.book_id = book_keyword.book_id" 
				+" JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id"  
				+" JOIN book_author ON book.book_id = book_author.book_id"  
				+" JOIN author ON book_author.author_id = author.author_id" 
				+" JOIN book_character ON book.book_id = book_character.book_id" 
				+" JOIN character ON book_character.character_id = character.character_id" 
				+" WHERE location.city = ? OR location.country = ?";
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForLocation, location, location);
		while(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnCharacter(String character) {
		String sqlQueryForCharacter = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+" FROM character"
				+" JOIN book_character ON character.character_id = book_character.character_id" 
				+" JOIN book ON book_character.book_id = book.book_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" JOIN book_keyword ON book.book_id = book_keyword.book_id"
				+" JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" WHERE character.f_name = ? OR character.l_name = ?";
		List<Book> books = new ArrayList<Book>();
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForCharacter, character, character);
		while(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnTitle(String title) {
		String sqlQueryForTitle = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+" FROM book"
				+" JOIN book_character ON book.book_id = book_character.book_id" 
				+" JOIN character ON book_character.character_id = character.character_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" JOIN book_keyword ON book.book_id = book_keyword.book_id"
				+" JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" WHERE book.title LIKE ? OR book.title = ? ";
		List<Book> books = new ArrayList<Book>();
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForTitle, "%" + title + "%", title);
		while(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}
	
	private List<Book> deleteDuplicateBooks(List<Book> books) {
		for (int i = 0; i < books.size(); i++) {
			Book bookToCheck = books.get(i);
			int bookCounter = 0;
			for (int j = 0; j < books.size(); j++) {
				if (bookToCheck.equals(books.get(j))) {
					bookCounter++;
				}
				if (bookCounter > 1) {
					books.remove(i);
				}
			}
		}
		return books;
	}
	
	private List<Book> mapBookToSqlRowSet(SqlRowSet sqlRowSet, List<Book> books) {
		Book newBook = createNewBook(sqlRowSet);
		if (containsBook(newBook, books)) {
			for (int j = 0; j < books.size(); j++) {
				if (authorNotAlreadyInBook(newBook, books, j)) {
					books.get(j).getAuthorFirstNames().add(newBook.getAuthorFirstNames().get(0));
					books.get(j).getAuthorLastNames().add(newBook.getAuthorLastNames().get(0));
				}
				if (characterNotAlreadyInBook(newBook, books, j)) {
					books.get(j).getCharacterFirstNames().add(newBook.getCharacterFirstNames().get(0));
					books.get(j).getCharacterLastNames().add(newBook.getCharacterLastNames().get(0));
				}
				if (keywordNotAlreadyInBook(newBook, books, j)) {
					books.get(j).getKeywords().add(newBook.getKeywords().get(0));
				}
			
			}
		} else {
			books.add(newBook);
		}
		return books;
	}
	
	private Book createNewBook(SqlRowSet sqlRowSet) {
		Book book = new Book();
		List<String> authorFirstNames = new ArrayList<String>();
		authorFirstNames.add(sqlRowSet.getString("author_first_name"));
		book.setAuthorFirstNames(authorFirstNames);
		
		List<String> authorLastNames = new ArrayList<String>();
		authorLastNames.add(sqlRowSet.getString("author_last_name"));
		book.setAuthorLastNames(authorLastNames);
		
		List<String> characterFirstNames = new ArrayList<String>();
		characterFirstNames.add(sqlRowSet.getString("character_first_name"));
		book.setCharacterFirstNames(authorFirstNames);
		
		List<String> characterLastNames = new ArrayList<String>();
		characterLastNames.add(sqlRowSet.getString("character_last_name"));
		book.setCharacterLastNames(authorLastNames);
		
		List<String> keywords = new ArrayList<String>();
		keywords.add(sqlRowSet.getString("word"));
		book.setKeywords(keywords);
		
		book.setBook_id(sqlRowSet.getLong("book_id"));
		book.setTitle(sqlRowSet.getString("title"));
		book.setCity(sqlRowSet.getString("city"));
		book.setCountry(sqlRowSet.getString("country"));
		
		return book;
		
	}
	
	
	private boolean authorNotAlreadyInBook(Book newBook, List<Book> books, int j) {
		return (!books.get(j).getAuthorFirstNames().contains(newBook.getAuthorFirstNames().get(0)) && 
				!books.get(j).getAuthorLastNames().contains(newBook.getAuthorLastNames().get(0)));
	}
	
	
	private boolean characterNotAlreadyInBook(Book newBook,List<Book> books, int j) {
		return (!books.get(j).getCharacterFirstNames().contains(newBook.getCharacterFirstNames().get(0)) && 
				!books.get(j).getCharacterLastNames().contains(newBook.getCharacterLastNames().get(0)));
	}
	
	private boolean keywordNotAlreadyInBook(Book newBook,List<Book> books, int j) {
		return (!books.get(j).getKeywords().contains(newBook.getKeywords().get(0)));
	}
	
	private boolean containsBook(Book newBook, List<Book> books) {
		for (Book book : books) {
			if (book.equals(newBook)) {
				return true;
			}
		}
		return false;
	}
	
	
}
