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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String searchBookResults(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		String bookQuery = request.getParameter("queryString");
		System.out.println(bookQuery);
		List<Book> books = bookDAO.searchForBooks(bookQuery);
		if (books.size() == 0) {
			redirectAttributes.addFlashAttribute("bookSize", books);
			redirectAttributes.addFlashAttribute("messageNoBooks", " There were no books found. Please try a different search.");
			return "redirect:/";
			
		}
		
		request.setAttribute("books", books);
		
		return "homepage"; //links to JSP page


	}
	
	
	
}
