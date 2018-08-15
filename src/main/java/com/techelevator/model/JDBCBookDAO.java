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
		saveBookTitle(book.getTitle().toLowerCase());
		long bookId = getLastBookId();
		saveLocation(book.getSection().toLowerCase(), bookId);
		saveCharacterNames(book.getCharacterFirstNames(), book.getCharacterLastNames(), bookId);
		saveAuthorNames(book.getAuthorFirstNames(), book.getAuthorLastNames(), bookId);
		saveKeywords(book.getKeywords(), bookId);
	}
	
	private void saveBookTitle(String title) {
		String sqlInsertBookTitle = "INSERT INTO book (title) VALUES (?)";
		jdbcTemplate.update(sqlInsertBookTitle, title);
	}
	
	private void saveLocation(String section, long bookId) {
		String sqlInsertBookSection = "INSERT INTO location (section) VALUES (?)";
		String sqlGetLastLocationId = "SELECT location_id FROM location ORDER BY location_id DESC LIMIT 1";
		String sqlInsertLocationIdAndBookId = "INSERT INTO book_location (book_id, location_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlInsertBookSection, section);
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastLocationId);
		long locationId = result.getLong("location_id");
		
		jdbcTemplate.update(sqlInsertLocationIdAndBookId, bookId, locationId);
	}
	
	private void saveCharacterNames(List<String> characterFirstNames, List<String> characterLastNames, long bookId) {
		String sqlInsertCharacterFirstName = "INSERT INTO character (f_name, l_name) VALUES (?, ?)";
		String sqlInsertCharacterIdAndBookId = "INSERT INTO book_character (book_id, character_id) VALUES (?, ?)";
		String sqlGetLastCharacterId = "SELECT character_id FROM character ORDER BY character_id DESC LIMIT 1";
		long characterId;
		
		for (int i = 0; i < characterFirstNames.size(); i++) {
			jdbcTemplate.update(sqlInsertCharacterFirstName, characterFirstNames.get(i), characterLastNames.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastCharacterId);
			characterId = result.getLong("character_id");
			jdbcTemplate.update(sqlInsertCharacterIdAndBookId, bookId, characterId);
		}
	}
	
	private void saveAuthorNames(List<String> authorFirstNames, List<String> authorLastNames, long bookId) {
		String sqlInsertAuthorFirstName = "INSERT INTO author (f_name, l_name) VALUES (?, ?)";
		String sqlInsertAuthorIdAndBookId = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
		String sqlGetLastAuthorId = "SELECT author_id FROM author ORDER BY author_id DESC LIMIT 1";
		long authorId;
		for (int i = 0; i < authorFirstNames.size(); i++) {
			jdbcTemplate.update(sqlInsertAuthorFirstName, authorFirstNames.get(i), authorLastNames.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastAuthorId);
			authorId = result.getLong("author_id");
			jdbcTemplate.update(sqlInsertAuthorIdAndBookId, bookId, authorId);
		}
	}
	
	private void saveKeywords(List<String> keywords, long bookId) {
		String sqlInsertKeyword = "INSERT INTO author (word) VALUES (?)";
		String sqlInsertKeywordIdAndBookId = "INSERT INTO keyword_book (book_id, keyword_id) VALUES (?, ?)";
		String sqlGetLastKeywordId = "SELECT keyword_id FROM keyword ORDER BY keyword_id DESC LIMIT 1";
		long keywordId;
		for (int i = 0; i < keywords.size(); i++) {
			jdbcTemplate.update(sqlInsertKeyword, keywords.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastKeywordId);
			keywordId = result.getLong("keyword_id");
			jdbcTemplate.update(sqlInsertKeywordIdAndBookId, bookId, keywordId);
		}
	}
	
	
	private long getLastBookId() {
		String sqlGetLastBookId = "SELECT book_id FROM book ORDER BY book_id DESC LIMIT 1";
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlGetLastBookId);
		long bookId = result.getLong("book_id");
		return bookId;
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
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, keyword.word" 
				+ " FROM author "
				+ " JOIN book_author ON book_author.author_id = author.author_id"
				+ " JOIN book ON book.book_id = book_author.book_id" 
				+ " JOIN book_character ON book.book_id = book_character.book_id"
				+ " JOIN character ON book_character.character_id = character.character_id"
				+ " JOIN book_location ON book.book_id = book_location.book_id"
				+ " JOIN location ON location.location_id = book_location.location_id"
				+ " JOIN book_keyword ON book.book_id = book_keyword.book_id"
				+ " JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id"   
				+ " WHERE author.f_name LIKE ? OR author.l_name LIKE ? ";
		
		SqlRowSet  results = jdbcTemplate.queryForRowSet(sqlQueryForAuthor, author + "%", author + "%");
		
		while(results.next()) {
			books = mapBookToSqlRowSet(results, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnKeyword(String keyword) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForKeyword = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, keyword.word" 
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
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, keyword.word" 
				+" FROM location"
				+" JOIN book_location ON location.location_id = book_location.location_id"
				+" JOIN book ON book.book_id = book_location.book_id" 
				+" JOIN book_keyword ON book.book_id = book_keyword.book_id" 
				+" JOIN keyword ON keyword.keyword_id = book_keyword.keyword_id"  
				+" JOIN book_author ON book.book_id = book_author.book_id"  
				+" JOIN author ON book_author.author_id = author.author_id" 
				+" JOIN book_character ON book.book_id = book_character.book_id" 
				+" JOIN character ON book_character.character_id = character.character_id" 
				+" WHERE location.section = ?";
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForLocation, location);
		while(result.next()) {
			books = mapBookToSqlRowSet(result, books);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnCharacter(String character) {
		String sqlQueryForCharacter = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, keyword.word" 
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
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, keyword.word" 
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
		Book bookToCheck;
		for (int i = 0; i < books.size(); i++) {
			bookToCheck = books.get(i);
			int bookCounter = 0;
			for (int j = 0; j < books.size(); j++) {
				if (bookToCheck.equals(books.get(j))) {
					bookCounter++;
				}
				if (bookCounter > 1) {
					books.remove(i);
					break;
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
		book.setAuthorFirstNames(createAuthorFirstNames(sqlRowSet));
		book.setAuthorLastNames(createAuthorLastNames(sqlRowSet));
		book.setCharacterFirstNames(createCharacterFirstNames(sqlRowSet));
		book.setCharacterLastNames(createCharacterLastNames(sqlRowSet));
		book.setKeywords(createKeywords(sqlRowSet));
		book.setBook_id(sqlRowSet.getLong("book_id"));
		book.setTitle(sqlRowSet.getString("title"));
		book.setSection(sqlRowSet.getString("section"));
		return book;
		
	}
	
	private List<String> createAuthorFirstNames(SqlRowSet sqlRowSet) {
		List<String> authorFirstNames = new ArrayList<String>();
		authorFirstNames.add(sqlRowSet.getString("author_first_name"));
		return authorFirstNames;
	}
	
	private List<String> createAuthorLastNames(SqlRowSet sqlRowSet) {
		List<String> authorLastNames = new ArrayList<String>();
		authorLastNames.add(sqlRowSet.getString("author_last_name"));
		return authorLastNames;
	}
	
	private List<String> createCharacterFirstNames(SqlRowSet sqlRowSet) {
		List<String> characterFirstNames = new ArrayList<String>();
		characterFirstNames.add(sqlRowSet.getString("author_last_name"));
		return characterFirstNames;
	}
	
	private List<String> createCharacterLastNames(SqlRowSet sqlRowSet) {
		List<String> characterLastNames = new ArrayList<String>();
		characterLastNames.add(sqlRowSet.getString("character_last_name"));
		return characterLastNames;
	}
	
	private List<String> createKeywords(SqlRowSet sqlRowSet) {
		List<String> keywords = new ArrayList<String>();
		keywords.add(sqlRowSet.getString("word"));
		return keywords;
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
