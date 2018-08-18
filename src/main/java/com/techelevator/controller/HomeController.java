package com.techelevator.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;

@Controller
@SessionAttributes("dateLastSearched")
public class HomeController {
	
	@Autowired
	private BookDAO bookDAO;

	@RequestMapping(path=("/"), method=RequestMethod.GET)
	public String showHomepage(ModelMap map) {
		if (map.get("dateLastSearched") == null) {
			Date curDate = new Date();
			map.put("dateLastSearched", curDate);
		}
	return "homepage";
	}
	
	
	@RequestMapping(path=("/search"), method=RequestMethod.GET)
	public String searchBookResults(HttpServletRequest request, final RedirectAttributes redirectAttributes, ModelMap map) {
		
		String bookQuery = request.getParameter("queryString");
		List<Book> books = bookDAO.searchForBooks(bookQuery);
		
		if (!(request.getParameter("newSearch") == null)) {
			books = books.stream().filter(book -> (book.getDateAdded().compareTo((Date) map.get("dateLastSearched"))) > 0).collect(Collectors.toList());
		} 
		
		Date curDate = new Date();
		map.put("dateLastSearched", curDate);
		
		if (books.size() == 0) {
			redirectAttributes.addFlashAttribute("bookSize", books);
			redirectAttributes.addFlashAttribute("messageNoBooks", " There were no books found. Please try a different search.");
			return "redirect:/";
			
		}
		
		request.setAttribute("books", books);
		
		return "homepage"; //links to JSP page


	}
	
	
	
}
