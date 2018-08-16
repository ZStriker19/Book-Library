package com.techelevator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;

@Controller
public class HomeController {
	
	@Autowired
	private BookDAO bookDAO;

	@RequestMapping(path=("/"), method=RequestMethod.GET)
	public String showHomepage() {
	return "homepage";
	}
	
	@RequestMapping(path=("/search"), method=RequestMethod.GET)
	public String searchBookResults(HttpServletRequest request) {
		String bookQuery = request.getParameter("queryString");
		List<Book> books = bookDAO.searchForBooks(bookQuery);
		if (books.size() == 0) {
			Book fakeBook = new Book();
			fakeBook.setTitle("There were no results found for your search. Please try a different query.");
			ArrayList<String> fakeAuthorFirstNames = new ArrayList<String>();
			fakeAuthorFirstNames.add("");
			ArrayList<String> fakeAuthorLastNames = new ArrayList<String>();
			fakeAuthorFirstNames.add("");
			fakeBook.setAuthorFirstNames(fakeAuthorFirstNames);
			fakeBook.setAuthorLastNames(fakeAuthorLastNames);
			
			books.add(fakeBook);
			request.setAttribute("books", books);
		} else {
			request.setAttribute("books", books);
		}
		return "homepage"; //links to JSP page
	}
	
	
	
}
