package com.techelevator.model.forum;

import java.util.Date;

public class Forum {
	private Long forumPostId;
	private String username;
	private String subject;
	private String message;
	private Date datePosted;
	
	public Long getForumPostId() {
		return forumPostId;
	}
	public void setForumPostId(Long forumPostId) {
		this.forumPostId = forumPostId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
}
