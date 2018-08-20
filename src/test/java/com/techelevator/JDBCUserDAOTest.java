package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.book.Book;
import com.techelevator.model.user.JDBCUserDAO;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDAO;
import com.techelevator.security.PasswordHasher;

public class JDBCUserDAOTest extends DAOIntegrationTest {
	
	private UserDAO userDao;
	private PasswordHasher hashMaster;
	
	@Before
	public void setup() {
		userDao = new JDBCUserDAO(dataSource, hashMaster);
	}
	
	@Test 
	public void get_username_and_password() throws SQLException {
		String userName = "system_admin";
		String password = "Systemadministrator";
		String userName2 = "test_user";
		String password2 = "Testpassword";
		
		boolean userNotInDatabase = userDao.searchForUsernameAndPassword(userName2, password2);
		boolean isUserInDatabase = userDao.searchForUsernameAndPassword(userName, password);
		
		Assert.assertNotNull(isUserInDatabase);
		Assert.assertTrue(isUserInDatabase);
		Assert.assertFalse(userNotInDatabase);			
	}
	
	@Test
	public void save_user() throws SQLException {
		String userName = "test_user";
		String password = "Testpassword";
		userDao.saveUser(userName, password);
		
		boolean isUserInDatabase = userDao.searchForUsernameAndPassword(userName, password);
		
		Assert.assertNotNull(isUserInDatabase);
		Assert.assertTrue(isUserInDatabase);
	}
}
