package com.techelevator;

import java.sql.SQLException;
import java.util.List;

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
		List<Book> books = bookDao.searchForBooksBasedOnAuthor("harry");
		
	}
}
