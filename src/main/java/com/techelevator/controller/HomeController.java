package com.techelevator.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.book.Book;
import com.techelevator.model.book.BookDAO;
import com.techelevator.model.user.User;

@Controller
@SessionAttributes("dateLastSearched")
public class HomeController {
	
	@Autowired
	private BookDAO bookDao;

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
		List<Book> books = bookDao.searchForBooks(bookQuery);
		
		if (request.getParameter("newSearch") != null) {
			books = books.stream().filter(book -> (book.getDateAdded().compareTo((Date) map.get("dateLastSearched"))) > 0).collect(Collectors.toList());
		} 
		
		Date curDate = new Date();
		map.put("dateLastSearched", curDate);
		
		if (books.size() == 0) {
			redirectAttributes.addFlashAttribute("bookSize", books);
			redirectAttributes.addFlashAttribute("messageNoBooks", " There were no books found. Please try a different search.");
			return "redirect:/";
			
		}
		
		redirectAttributes.addFlashAttribute("books", books);
		request.setAttribute("books", books);
		
		return "homepage";
	}
	
	@RequestMapping(path=("/addToWillReadList"), method=RequestMethod.POST)
	public String addBookToWillReadList(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.saveBookUserWillReadList(currentUser.getId(), bookId);
		    }
		    
		
		return "redirect:/readingLists";
	}
	
	@RequestMapping(path=("/addToHaveReadList"), method=RequestMethod.POST)
	public String addBookToHaveReadList(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		
		User currentUser = (User) session.getAttribute("currentUser");
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		        bookDao.saveBookUserHaveReadList(currentUser.getId(), bookId);
		    }
		    
		
		
		return "redirect:/readingLists";
	}
	
	@RequestMapping(path=("/deleteBook"), method=RequestMethod.POST)
	public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		
		Enumeration<String> enumeration = request.getParameterNames();
		    while(enumeration.hasMoreElements()){
		    	long bookId = Long.parseLong((String) request.getParameter(enumeration.nextElement()));
		    	bookDao.deleteBook(bookId);
		    }
		    
		
		return "redirect:/";
	}
	
	
	
}
