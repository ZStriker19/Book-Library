package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForAuthor, author, author);
		if(result.next()) {
			books = mapBookToSqlRowSet(result, books);
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
		if(result.next()) {
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
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForLocation, location);
		if(result.next()) {
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
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForCharacter, character);
		if(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnTitle(String title) {
		String sqlQueryForTitle = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.city, location.country, keyword.word" 
				+"FROM book"
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
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForTitle, title);
		if(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}
	
	private List<Book> mapBookToSqlRowSet(SqlRowSet sqlRowSet, List<Book> books) {
		boolean isNewBook = true;
		long book_id = sqlRowSet.getLong("book_id");
		for (int j = 0; j < books.size(); j++) {
			isNewBook = false;
			if (book_id == books.get(j).getBookId()) {
				if (!books.get(j).getAuthorFirstNames().contains(sqlRowSet.getString("author_first_name")) && !books.get(j).getAuthorLastNames().contains(sqlRowSet.getString("author_last_name"))) {
					books.get(j).getAuthorFirstNames().add(sqlRowSet.getString("author_first_name"));
					books.get(j).getAuthorLastNames().add(sqlRowSet.getString("author_last_name"));
				}
				if (!books.get(j).getCharacterFirstNames().contains(sqlRowSet.getString("character_first_name")) && !books.get(j).getCharacterLastNames().contains(sqlRowSet.getString("character_last_name"))) {
					books.get(j).getCharacterFirstNames().add(sqlRowSet.getString("character_first_name"));
					books.get(j).getCharacterLastNames().add(sqlRowSet.getString("character_last_name"));
				}
				if (!books.get(j).getKeywords().contains(sqlRowSet.getString("word"))) {
					books.get(j).getKeywords().add(sqlRowSet.getString("word"));
				}
				
			}
		}
		if (isNewBook) {
			Book book = new Book();
			List<String> authorFirstNames = new ArrayList<String>();
			authorFirstNames.add(sqlRowSet.getString("author_first_name"));
			book.setAuthorFirstNames(authorFirstNames);
			
			List<String> authorLastNames = new ArrayList<String>();
			authorLastNames.add(sqlRowSet.getString("author_last_name"));
			book.setAuthorLastNames(authorLastNames);
			
			List<String> characterFirstNames = new ArrayList<String>();
			characterFirstNames.add(sqlRowSet.getString("character_first_name"));
			book.setAuthorFirstNames(authorFirstNames);
			
			List<String> characterLastNames = new ArrayList<String>();
			characterLastNames.add(sqlRowSet.getString("character_last_name"));
			book.setCharacterLastNames(authorLastNames);
			
			List<String> keywords = new ArrayList<String>();
			keywords.add(sqlRowSet.getString("word"));
			book.setKeywords(keywords);
			
			book.setBook_id(book_id);
			book.setTitle(sqlRowSet.getString("title"));
			book.setCity(sqlRowSet.getString("city"));
			book.setCountry(sqlRowSet.getString("country"));
			books.add(book);
		}
		
		return books;
	}
	
	private List<Book> deleteDuplicateBooks(List<Book> books) {
		List<Book> booksWithoutDuplicates = books.stream().distinct().collect(Collectors.toList());
		return booksWithoutDuplicates;
	}
	
	

}
