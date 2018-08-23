package com.techelevator.model.book;

import java.util.List;

public interface BookDAO {

	public void saveBook(Book book);

	public List<Book> searchForBooks(String queryString);
	
	public List<Book> searchForBooksUserHasRead(long appUserId);
	
	public List<Book> searchForBooksUserWillRead(long appUserId);
	
	public void saveBookUserWillReadList(long appUserId, long bookId);
	
	public void saveBookUserHaveReadList(long appUserId, long bookId);
	
	public void deleteBookFromUserWillReadList(long appUserId, long bookId);
	
	public void deleteBookFromUserHasReadList(long appUserId, long bookId);
	
	
}
