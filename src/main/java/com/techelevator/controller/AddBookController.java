package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;
import com.techelevator.model.JDBCBookDAO;



@Controller
public class AddBookController {

		
		@RequestMapping(path=("addBook"), method=RequestMethod.GET)
		public String showAddBookPage() {
		return "/addBook";
		}
		
		@RequestMapping(path=("/addBook/submit"), method=RequestMethod.POST)
		public String createNewBook(HttpServletRequest request) {
			String title = (String) request.getParameter("title-input");
			String section = (String) request.getParameter("section-input");
			String firstName = (String) request.getParameter("first-name-input");
			String lastName = (String) request.getParameter("last-name-input");
			String characterFirst = (String) request.getParameter("character-1-first-name-input");
			String characterLast = (String) request.getParameter("character-1-last-name-input");
			String keyword = (String) request.getParameter("keyword-input");
			System.out.println(title);
			System.out.println(section);
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(characterFirst);
			System.out.println(characterLast);
			System.out.println(keyword);
			Book book = new Book();
			List<String> authorFirstNames = new ArrayList<String>();
			authorFirstNames.add(firstName);
			book.setAuthorFirstNames(authorFirstNames);
			
			return "/addBook";
			
		}
}

