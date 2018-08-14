package com.techelevator;

import java.sql.SQLException;
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
//		System.out.println("books once function completes: " + books.get(0).getBookId());
		Assert.assertNotNull(books);
		Assert.assertEquals(3, books.size());
	}
	
	@Test
	public void get_books_from_character_table() throws SQLException {
		String queryString = "harry";
		List<Book> books = bookDao.searchForBooksBasedOnCharacter(queryString);
		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());
	}
}
