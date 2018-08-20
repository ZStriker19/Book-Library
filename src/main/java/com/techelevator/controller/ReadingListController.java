package com.techelevator.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class ReadingListController {
	private BookDAO bookDao;

	@Autowired
	public ReadingListController(BookDAO bookDao) {
		this.bookDao = bookDao;
	}
	
	@RequestMapping(path="/readingLists")
	public String readingList(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<Book> booksToRead = bookDao.searchForBooksUserWillRead(currentUser.getId());
		List<Book> booksHaveRead = bookDao.searchForBooksUserHasRead(currentUser.getId());
		request.setAttribute("booksToRead", booksToRead);
		request.setAttribute("booksHaveRead", booksHaveRead);
		
		return "readingList";
		
	}
	
	@RequestMapping(path="/addBooksToRead")
	public String addBooksToRead(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.deleteBookFromUserHasReadList(currentUser.getId(), bookId);
		        bookDao.saveBookUserWillReadList(currentUser.getId(), bookId);
		        
		    }
		    
		List<Book> booksToRead = bookDao.searchForBooksUserWillRead(currentUser.getId());
		List<Book> booksHaveRead = bookDao.searchForBooksUserHasRead(currentUser.getId());
		request.setAttribute("booksToRead", booksToRead);
		request.setAttribute("booksHaveRead", booksHaveRead);
		
		return "readingList";
		
	}
	
	@RequestMapping(path="/addToBooksYouHaveRead")
	public String addBooksYouHaveRead(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		        long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.deleteBookFromUserWillReadList(currentUser.getId(), bookId);
		        bookDao.saveBookUserHaveReadList(currentUser.getId(), bookId);
		    }
		    
		List<Book> booksToRead = bookDao.searchForBooksUserWillRead(currentUser.getId());
		List<Book> booksHaveRead = bookDao.searchForBooksUserHasRead(currentUser.getId());
		request.setAttribute("booksToRead", booksToRead);
		request.setAttribute("booksHaveRead", booksHaveRead);
		
		return "readingList";
		
	}
	
	@RequestMapping(path="/deleteFromBooksToRead")
	public String deleteFromBooksToRead(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.deleteBookFromUserWillReadList(currentUser.getId(), bookId);
		    }
		    
		List<Book> booksToRead = bookDao.searchForBooksUserWillRead(currentUser.getId());
		List<Book> booksHaveRead = bookDao.searchForBooksUserHasRead(currentUser.getId());
		request.setAttribute("booksToRead", booksToRead);
		request.setAttribute("booksHaveRead", booksHaveRead);
		
		return "readingList";
		
	}
	
	
	
	@RequestMapping(path="/deleteFromBooksYouHaveRead")
	public String deleteFromBooksYouHaveRead(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.deleteBookFromUserHasReadList(currentUser.getId(), bookId);
		    }
		    
		List<Book> booksToRead = bookDao.searchForBooksUserWillRead(currentUser.getId());
		List<Book> booksHaveRead = bookDao.searchForBooksUserHasRead(currentUser.getId());
		request.setAttribute("booksToRead", booksToRead);
		request.setAttribute("booksHaveRead", booksHaveRead);
		
		return "readingList";
		
	}

}
