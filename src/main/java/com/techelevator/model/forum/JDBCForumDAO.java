package com.techelevator.model.forum;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCForumDAO implements ForumDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCForumDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Forum> getAllPosts() {
		List<Forum> allPosts = new ArrayList<>();
		String sqlSelectAllPosts = "SELECT forum_post_id, app_user.user_name, subject, message, post_date "+
								   "FROM forum_post "+ 
								   "JOIN app_user ON forum_post.app_user_id = app_user.app_user_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllPosts);
		while(results.next()) {
			Forum post = new Forum();
			post.setForumPostId(results.getLong("forumPostId"));
			post.setUsername(results.getString("username"));
			post.setSubject(results.getString("subject"));
			post.setMessage(results.getString("message"));
			post.setDatePosted(results.getDate("post_date"));
				
			allPosts.add(post);
		}
		return allPosts;
	}

	@Override
	public void save(Forum post) {
		// TODO Auto-generated method stub

	}

}
