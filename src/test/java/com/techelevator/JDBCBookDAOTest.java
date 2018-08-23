package com.techelevator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.book.Book;
import com.techelevator.model.book.BookDAO;
import com.techelevator.model.book.JDBCBookDAO;


public class JDBCBookDAOTest extends DAOIntegrationTest {
	private BookDAO bookDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		bookDao = new JDBCBookDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	@Test
	public void get_books_from_search_for_books() throws SQLException {
		String queryString = "harry";
		List<Book> books = bookDao.searchForBooks(queryString);
		
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
		
		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());
		Assert.assertEquals(books.get(0).getSection(), "A1");
		Assert.assertEquals(books.get(0).getAuthorFullNames(), "Neil Gaiman, Terry Pratchett");
		Assert.assertEquals(books.get(0).getCharacterLastNames().size(), 2);
		Assert.assertFalse(books.get(0).getCharacterLastNames().get(0).equals(books.get(0).getCharacterFirstNames().get(0)));
	}
	
	@Test
	public void get_books_user_has_read() throws SQLException {
		long app_user_id = 1;
		List<Book> books = bookDao.searchForBooksUserHasRead(app_user_id);
		
		Assert.assertNotNull(books);
		Assert.assertEquals(3, books.size());
		Assert.assertEquals("J.k. Rowling", books.get(0).getAuthorFullNames());
		Assert.assertEquals(5, books.get(0).getBookId());
		Assert.assertEquals(1, books.get(0).getAuthorFirstNames().size());
	}
	
	@Test
	public void get_books_user_will_read() throws SQLException {
		long appUserId = 1;
		List<Book> books = bookDao.searchForBooksUserWillRead(appUserId);
		
		Assert.assertNotNull(books);
		Assert.assertEquals(3, books.size());
		Assert.assertEquals("J.k. Rowling", books.get(0).getAuthorFullNames());
		Assert.assertEquals(1, books.get(0).getBookId());
		Assert.assertEquals(1, books.get(0).getAuthorFirstNames().size());
	}
	
	@Test
	public void add_book_to_will_read_list() throws SQLException {
		long appUserId = 1;
		long bookId = 9;
		List<Book> booksBeforeAdding = bookDao.searchForBooksUserWillRead(appUserId);
		
		bookDao.saveBookUserWillReadList(appUserId, bookId);
		
		List<Book> booksAfterAdding = bookDao.searchForBooksUserWillRead(appUserId);
		
		Assert.assertNotNull(booksAfterAdding);
		Assert.assertTrue(booksBeforeAdding.size() + 1 == booksAfterAdding.size());
	}
	
	@Test
	public void add_book_to_have_read_list() throws SQLException {
		long appUserId = 1;
		long bookId = 9;
		List<Book> booksBeforeAdding = bookDao.searchForBooksUserHasRead(appUserId);
		
		bookDao.saveBookUserHaveReadList(appUserId, bookId);
		
		List<Book> booksAfterAdding = bookDao.searchForBooksUserHasRead(appUserId);
		
		Assert.assertNotNull(booksAfterAdding);
		Assert.assertTrue(booksBeforeAdding.size() + 1 == booksAfterAdding.size());
	}
	
	@Test
	public void delete_book_from_have_read_list() throws SQLException {
		long appUserId = 1;
		long bookId = 5;
		List<Book> booksBeforeDeleting = bookDao.searchForBooksUserHasRead(appUserId);
		
		bookDao.deleteBookFromUserHasReadList(appUserId, bookId);
		
		List<Book> booksAfterDeleting = bookDao.searchForBooksUserHasRead(appUserId);
		
		Assert.assertTrue(booksBeforeDeleting.size() - 1 == booksAfterDeleting.size());
		
	}
	
	@Test
	public void delete_book_from_will_read_list() throws SQLException {
		long appUserId = 1;
		long bookId = 2;
		List<Book> booksBeforeDeleting = bookDao.searchForBooksUserWillRead(appUserId);
		
		bookDao.deleteBookFromUserWillReadList(appUserId, bookId);
		
		List<Book> booksAfterDeleting = bookDao.searchForBooksUserWillRead(appUserId);
		
		Assert.assertTrue(booksBeforeDeleting.size() - 1 == booksAfterDeleting.size());
		
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
		String characterTwoLastName = "greg";
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
