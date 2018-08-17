package com.techelevator.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
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
		saveToBook(book.getTitle().toLowerCase());
		long bookId = getLastBookId();
		saveLocation(book.getSection().toLowerCase(), bookId);
		saveCharacterNames(book.getCharacterFirstNames(), book.getCharacterLastNames(), bookId);
		saveAuthorNames(book.getAuthorFirstNames(), book.getAuthorLastNames(), bookId);
		saveGenres(book.getGenres(), bookId);
	}
	
	private void saveToBook(String title) {
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat();
		String dateToStr = format.format(curDate);

		String sqlInsertBookTitle = "INSERT INTO book (title, date_added) VALUES (?, ?)";
		jdbcTemplate.update(sqlInsertBookTitle, title, dateToStr);
	}
	
	
	private void saveLocation(String section, long bookId) {
		String sqlInsertBookSection = "INSERT INTO location (section) VALUES (?)";
		String sqlGetLastLocationId = "SELECT location_id FROM location ORDER BY location_id DESC LIMIT 1";
		String sqlInsertLocationIdAndBookId = "INSERT INTO book_location (book_id, location_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlInsertBookSection, section);
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastLocationId);
		
		long locationId = 0;
		if(result.next()) {
			locationId = result.getLong("location_id");
		}
		jdbcTemplate.update(sqlInsertLocationIdAndBookId, bookId, locationId);
	}
	
	private void saveCharacterNames(List<String> characterFirstNames, List<String> characterLastNames, long bookId) {
		String sqlInsertCharacterFirstName = "INSERT INTO character (f_name, l_name) VALUES (?, ?)";
		String sqlInsertCharacterIdAndBookId = "INSERT INTO book_character (book_id, character_id) VALUES (?, ?)";
		String sqlGetLastCharacterId = "SELECT character_id FROM character ORDER BY character_id DESC LIMIT 1";
		long characterId = 0;
		
		for (int i = 0; i < characterFirstNames.size(); i++) {
			jdbcTemplate.update(sqlInsertCharacterFirstName, characterFirstNames.get(i), characterLastNames.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastCharacterId);
			
			if(result.next()) {
				characterId = result.getLong("character_id");
			}
			jdbcTemplate.update(sqlInsertCharacterIdAndBookId, bookId, characterId);
		}
	}
	
	private void saveAuthorNames(List<String> authorFirstNames, List<String> authorLastNames, long bookId) {
		String sqlInsertAuthorFirstName = "INSERT INTO author (f_name, l_name) VALUES (?, ?)";
		String sqlInsertAuthorIdAndBookId = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
		String sqlGetLastAuthorId = "SELECT author_id FROM author ORDER BY author_id DESC LIMIT 1";
		long authorId = 0;
		for (int i = 0; i < authorFirstNames.size(); i++) {
			jdbcTemplate.update(sqlInsertAuthorFirstName, authorFirstNames.get(i), authorLastNames.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastAuthorId);
			
			if (result.next()) {
				authorId = result.getLong("author_id");
			}
			jdbcTemplate.update(sqlInsertAuthorIdAndBookId, bookId, authorId);
		}
	}
	
	private void saveGenres(List<String> genres, long bookId) {
		String sqlInsertGenre = "INSERT INTO genre (genre) VALUES (?)";
		String sqlInsertGenreIdAndBookId = "INSERT INTO book_genre (book_id, genre_id) VALUES (?, ?)";
		String sqlGetLastGenreId = "SELECT genre_id FROM genre ORDER BY genre_id DESC LIMIT 1";
		long genreId = 0;
		for (int i = 0; i < genres.size(); i++) {
			jdbcTemplate.update(sqlInsertGenre, genres.get(i));
			
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetLastGenreId);
			if (result.next()) {
				genreId = result.getLong("genre_id");
			}
			
			jdbcTemplate.update(sqlInsertGenreIdAndBookId, bookId, genreId);
		}
	}
	
	private long getLastBookId() {
		String sqlGetLastBookId = "SELECT book_id FROM book ORDER BY book_id DESC LIMIT 1";
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlGetLastBookId);
		long bookId = 0;
		if (result.next()) {
			bookId = result.getLong("book_id");
		}
		return bookId;
	}
	
	

	public List<Book> searchForBooks(String queryString) {
		queryString = queryString.toLowerCase();
		
		List<Book> allBooksFromQueries = new ArrayList<Book>();
		
		allBooksFromQueries.addAll(searchForBooksBasedOnAuthor(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnGenre(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnPublishingLocation(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnCharacter(queryString));
		allBooksFromQueries.addAll(searchForBooksBasedOnTitle(queryString));
		
	
		List<Book> booksWithoutDuplicates = combineBooks(allBooksFromQueries);
		List<Book> booksWithoutDuplicates2 = booksWithoutDuplicates;
		
		
		return booksWithoutDuplicates2;
	}

	
	public List<Book> searchForBooksBasedOnAuthor(String author) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForAuthor = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, genre.genre" 
				+ " FROM author "
				+ " JOIN book_author ON book_author.author_id = author.author_id"
				+ " JOIN book ON book.book_id = book_author.book_id" 
				+ " JOIN book_character ON book.book_id = book_character.book_id"
				+ " JOIN character ON book_character.character_id = character.character_id"
				+ " JOIN book_location ON book.book_id = book_location.book_id"
				+ " JOIN location ON location.location_id = book_location.location_id"
				+ " JOIN book_genre ON book.book_id = book_genre.book_id"
				+ " JOIN genre ON genre.genre_id = book_genre.genre_id"   
				+ " WHERE author.f_name LIKE ? OR author.l_name LIKE ? ";
		
		SqlRowSet  results = jdbcTemplate.queryForRowSet(sqlQueryForAuthor, author + "%", author + "%");
		
		while(results.next()) {
			Book book = createNewBook(results);
			books.add(book);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnGenre(String genre) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForGenre = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, genre.genre" 
				+" FROM genre" 
				+" JOIN book_genre ON genre.genre_id = book_genre.genre_id" 
				+" JOIN book ON book.book_id = book_genre.book_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" JOIN book_character ON book.book_id = book_character.book_id"
				+" JOIN character ON book_character.character_id = character.character_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" WHERE genre.genre = ?";
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForGenre, genre);
		while(result.next()) {
			Book book = createNewBook(result);
			books.add(book);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnPublishingLocation(String location) {
		List<Book> books = new ArrayList<Book>();
		String sqlQueryForLocation = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, genre.genre" 
				+" FROM location"
				+" JOIN book_location ON location.location_id = book_location.location_id"
				+" JOIN book ON book.book_id = book_location.book_id" 
				+" JOIN book_genre ON book.book_id = book_genre.book_id" 
				+" JOIN genre ON genre.genre_id = book_genre.genre_id"  
				+" JOIN book_author ON book.book_id = book_author.book_id"  
				+" JOIN author ON book_author.author_id = author.author_id" 
				+" JOIN book_character ON book.book_id = book_character.book_id" 
				+" JOIN character ON book_character.character_id = character.character_id" 
				+" WHERE location.section = ?";
		
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForLocation, location);
		while(result.next()) {
			Book book = createNewBook(result);
			books.add(book);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnCharacter(String character) {
		String sqlQueryForCharacter = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, genre.genre" 
				+" FROM character"
				+" JOIN book_character ON character.character_id = book_character.character_id" 
				+" JOIN book ON book_character.book_id = book.book_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" JOIN book_genre ON book.book_id = book_genre.book_id"
				+" JOIN genre ON genre.genre_id = book_genre.genre_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" WHERE character.f_name = ? OR character.l_name = ?";
		List<Book> books = new ArrayList<Book>();
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForCharacter, character, character);
		while(result.next()) {
			Book book = createNewBook(result);
			books.add(book);
		}
		return books;
	}

	
	public List<Book> searchForBooksBasedOnTitle(String title) {
		String sqlQueryForTitle = "SELECT book.book_id, book.title, author.f_name AS author_first_name, author.l_name AS author_last_name,"
				+ " character.f_name AS character_first_name, character.l_name AS character_last_name, location.section, genre.genre" 
				+" FROM book"
				+" JOIN book_character ON book.book_id = book_character.book_id" 
				+" JOIN character ON book_character.character_id = character.character_id"
				+" JOIN book_location ON book.book_id = book_location.book_id"
				+" JOIN location ON location.location_id = book_location.location_id"
				+" JOIN book_genre ON book.book_id = book_genre.book_id"
				+" JOIN genre ON genre.genre_id = book_genre.genre_id" 
				+" JOIN book_author ON book.book_id = book_author.book_id" 
				+" JOIN author ON book_author.author_id = author.author_id"
				+" WHERE book.title LIKE ? OR book.title = ? ";
		List<Book> books = new ArrayList<Book>();
		SqlRowSet  result = jdbcTemplate.queryForRowSet(sqlQueryForTitle, "%" + title + "%", title);
		while(result.next()) {
			Book book = createNewBook(result);
			books.add(book);
		}
		return books;
	}
	
	private List<Book> deleteDuplicateBooks(List<Book> books) {
		List<Book> booksWithoutDuplicates = new ArrayList<Book>();
		
		for (int i = 0; i < books.size(); i++) {
			if (!containsBook(books.get(i), booksWithoutDuplicates)){
				booksWithoutDuplicates.add(books.get(i));
			}
		}
		
		return booksWithoutDuplicates;
	}
	
	private List<Book> combineBooks(List<Book> books) {
		Book newBook;
		Book comparedBook;
		for (int i=0; i<books.size(); i++) {
			newBook = books.get(i);
				for (int j = 0; j < books.size(); j++) {
					comparedBook = books.get(j);
					if (comparedBook.getBookId() == newBook.getBookId()) {
						if (authorNotAlreadyInBook(newBook, books, j)) {
							books.get(j).getAuthorFirstNames().add(newBook.getAuthorFirstNames().get(0));
							books.get(j).getAuthorLastNames().add(newBook.getAuthorLastNames().get(0));
						}
						if (characterNotAlreadyInBook(newBook, books, j)) {
							books.get(j).getCharacterFirstNames().add(newBook.getCharacterFirstNames().get(0));
							books.get(j).getCharacterLastNames().add(newBook.getCharacterLastNames().get(0));
						}
						if (genreNotAlreadyInBook(newBook, books, j)) {
							books.get(j).getGenres().add(newBook.getGenres().get(0));
						}
					}
				}
		}
		books = deleteDuplicateBooks(books);
		return books;
	}
	
	
	
	
	
	private Book createNewBook(SqlRowSet sqlRowSet) {
		Book book = new Book();
		book.setAuthorFirstNames(createAuthorFirstNames(sqlRowSet));
		book.setAuthorLastNames(createAuthorLastNames(sqlRowSet));
		book.setCharacterFirstNames(createCharacterFirstNames(sqlRowSet));
		book.setCharacterLastNames(createCharacterLastNames(sqlRowSet));
		book.setGenres(createGenres(sqlRowSet));
		book.setBook_id(sqlRowSet.getLong("book_id"));
		book.setTitle(sqlRowSet.getString("title"));
		book.setSection(sqlRowSet.getString("section"));
//		SimpleDateFormat format = new SimpleDateFormat();
//		Date dateAdded = null;
//		try {
//			dateAdded = format.parse(sqlRowSet.getString("date_added"));
//		} catch (InvalidResultSetAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		book.setDateAdded(dateAdded);
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
		characterFirstNames.add(sqlRowSet.getString("character_first_name"));
		return characterFirstNames;
	}
	
	private List<String> createCharacterLastNames(SqlRowSet sqlRowSet) {
		List<String> characterLastNames = new ArrayList<String>();
		characterLastNames.add(sqlRowSet.getString("character_last_name"));
		return characterLastNames;
	}
	
	private List<String> createGenres(SqlRowSet sqlRowSet) {
		List<String> genres = new ArrayList<String>();
		genres.add(sqlRowSet.getString("genre"));
		return genres;
	}
	
	
	private boolean authorNotAlreadyInBook(Book newBook, List<Book> books, int j) {
		return (!books.get(j).getAuthorFirstNames().contains(newBook.getAuthorFirstNames().get(0)) && 
				!books.get(j).getAuthorLastNames().contains(newBook.getAuthorLastNames().get(0)));
	}
	
	
	private boolean characterNotAlreadyInBook(Book newBook,List<Book> books, int j) {
		for (int i = 0; i < books.size(); i++) {
		}
		
		return (!books.get(j).getCharacterFirstNames().contains(newBook.getCharacterFirstNames().get(0)));
	}
	
	private boolean genreNotAlreadyInBook(Book newBook,List<Book> books, int j) {
		return (!books.get(j).getGenres().contains(newBook.getGenres().get(0)));
	}
	
	private boolean containsBook(Book newBook, List<Book> books) {
		for (Book book : books) {
			System.out.println("new book id " + newBook.getBookId());
			System.out.println("book id  already on list" + newBook.getBookId() + "\n");
			if (book.getBookId() == newBook.getBookId()) {
				System.out.println("\nfound book.getbookid " + book.getBookId());
				return true;
			}
		}
		return false;
	}
	
	
}
