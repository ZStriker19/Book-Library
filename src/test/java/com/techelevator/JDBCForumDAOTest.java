package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.forum.Forum;
import com.techelevator.model.forum.ForumDAO;
import com.techelevator.model.forum.JDBCForumDAO;

public class JDBCForumDAOTest extends DAOIntegrationTest{
	
		private ForumDAO forumDao;
		private JdbcTemplate jdbcTemplate;
		
		@Before
		public void setup() {
			forumDao = new JDBCForumDAO(dataSource);
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		@Test 
		public void get_all_posts() throws SQLException {
			
			List<Forum> forums = forumDao.getAllPosts();
			System.out.println(forums.get(2).getMessage());
			Assert.assertNotNull(forums);
			Assert.assertEquals(3, forums.size());
		}

}
