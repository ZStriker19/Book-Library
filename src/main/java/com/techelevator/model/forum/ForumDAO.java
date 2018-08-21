package com.techelevator.model.forum;

import java.util.List;

public interface ForumDAO {
	
	public List<Forum> getAllPosts();
	public void save(Forum post, Long user_id);
}
