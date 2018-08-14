package com.techelevator.controller;

import java.util.List;

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
	
	@RequestMapping(path=("/"), method=RequestMethod.POST)
	public String searchBookResults(HttpServletRequest request) {
		String book = request.getParameter("queryString");
		List<Book> bookSearch = bookDAO.searchForBooks(book);
		request.setAttribute("book", bookSearch);
		return "redirect:/"; //links to JSP page
	}
	
}
