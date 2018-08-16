package com.techelevator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;
import com.techelevator.model.JDBCBookDAO;


public class JDBCBookDAOTest extends DAOIntegrationTest {
	BookDAO bookDao;
	
	@Before
	public void setup() {
		bookDao = new JDBCBookDAO(dataSource);
	}
	
	@Test 
	public void get_books_from_author_table() throws SQLException {
		String queryString = "j.k.";
		List<Book> books = bookDao.searchForBooksBasedOnAuthor(queryString);
		Assert.assertNotNull(books);
		Assert.assertEquals(21, books.size());
	}
	
	@Test
	public void get_books_from_character_table() throws SQLException {
		String queryString = "harry";
		List<Book> books = bookDao.searchForBooksBasedOnCharacter(queryString);

		Assert.assertNotNull(books);
		Assert.assertEquals(7, books.size());
	}
	
	@Test
	public void get_books_from_genre_table() throws SQLException {
		String queryString = "fantasy";
		List<Book> books = bookDao.searchForBooksBasedOnGenre(queryString);
		Assert.assertNotNull(books);
		Assert.assertEquals(21, books.size());
	}
	
	@Test
	public void get_books_from_location_table() throws SQLException {
		String queryString = "A3";
		List<Book> books = bookDao.searchForBooksBasedOnPublishingLocation(queryString);
		Assert.assertNotNull(books);
		Assert.assertEquals(3, books.size());
	}
	
	@Test
	public void get_books_from_title_table() throws SQLException {
		String queryString = "the great gatsby";
		List<Book> books = bookDao.searchForBooksBasedOnTitle(queryString);
		Assert.assertNotNull(books);
		Assert.assertEquals(2, books.size());
	}
	
	@Test
	public void get_books_from_search_for_books() throws SQLException {
		String queryString = "harry";
		List<Book> books = bookDao.searchForBooks(queryString);
		for (int i = 0; i < books.size(); i++) {
			System.out.println("inside the test " + books.get(i).getCharacterFirstNames());
		}
		
		Assert.assertNotNull(books);
		Assert.assertEquals(7, books.size());
		Assert.assertEquals(1, books.get(0).getBookId());
		Assert.assertEquals(3, books.get(2).getCharacterFirstNames().size());
		Assert.assertEquals("A1", books.get(2).getSection());
	}
	
	@Test
	public void get_books_from_search_for_books_with_multiple_authors() throws SQLException {
		String queryString = "lance";
		List<Book> books = bookDao.searchForBooks(queryString);
		
		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());
		Assert.assertEquals(9, books.get(0).getBookId());
		Assert.assertEquals(2, books.get(0).getAuthorFirstNames().size());
	}
	
	@Test
	public void save_book() throws SQLException {
		Book book = createBook();
		bookDao.saveBook(book);
		
		List<Book> books = bookDao.searchForBooks("good omens");
		for (Book booker : books) {
			System.out.println((booker.getBookId()));
		}
		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());
		Assert.assertEquals(books.get(0).getSection(), "A1");
		Assert.assertEquals(books.get(0).getAuthorFullNames(), "Neil Gaiman, Terry Pratchett");
		Assert.assertEquals(books.get(0).getCharacterLastNames().size(), 2);
	}
	
	private Book createBook() {
		Book book = new Book();
		String title = "good omens";
		String section = "A1";
		String genreOne = "fantasy";
		String genreTwo = "fiction";
		String characterOneFirstName = "crowley";
		String characterTwoFirstName = "adam";
		String characterOneLastName = "satan";
		String characterTwoLastName = null;
		String authorOneFirstName = "neil";
		String authorTwoFirstName = "terry";
		String authorOneLastName = "gaiman";
		String authorTwoLastName = "pratchett";
		
		List<String> genres = new ArrayList<String>();
		genres.add(genreOne);
		genres.add(genreTwo);
		
		List<String> authorFirstNames = new ArrayList<String>();
		List<String> authorLastNames = new ArrayList<String>();
		authorFirstNames.add(authorOneFirstName);
		authorFirstNames.add(authorTwoFirstName);
		authorLastNames.add(authorOneLastName);
		authorLastNames.add(authorTwoLastName);
		
		
		List<String> characterFirstNames = new ArrayList<String>();
		List<String> characterLastNames = new ArrayList<String>();
		characterFirstNames.add(characterOneFirstName);
		characterFirstNames.add(characterTwoFirstName);
		characterLastNames.add(characterOneLastName);
		characterLastNames.add(characterTwoLastName);
		
		book.setAuthorFirstNames(authorFirstNames);
		book.setAuthorLastNames(authorLastNames);
		book.setCharacterFirstNames(characterFirstNames);
		book.setCharacterLastNames(characterLastNames);
		book.setGenres(genres);
		book.setSection(section);
		book.setTitle(title);
		
		return book;
		
	}
}
