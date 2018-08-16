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
	
	@Autowired 
	private BookDAO bookDAO;
		
		@RequestMapping(path=("addBook"), method=RequestMethod.GET)
		public String showAddBookPage() {
		return "/addBook";
		}
		
		@RequestMapping(path=("/addBook/submit"), method=RequestMethod.POST)
		public String createNewBook(HttpServletRequest request) {
			String title = (String) request.getParameter("title-input");
			String section = (String) request.getParameter("section-input");
			String firstNames = (String) request.getParameter("first-name-input");
			String lastNames = (String) request.getParameter("last-name-input");
			String characterFirst = (String) request.getParameter("character-1-first-name-input");
			String characterLast = (String) request.getParameter("character-1-last-name-input");
			String genre = (String) request.getParameter("genres-input");
			System.out.println(title);
			System.out.println(section);
			System.out.println(firstNames);
			System.out.println(lastNames);
			System.out.println(characterFirst);
			System.out.println(characterLast);
			System.out.println(genre);
			
			Book book = new Book();
			
			book.setTitle(title);
			book.setSection(section);
			
			List<String> authorFirstNames = new ArrayList<String>();
			authorFirstNames.add(firstNames);
			book.setAuthorFirstNames(authorFirstNames);
			
			List<String> authorLastNames = new ArrayList<String>();
			authorLastNames.add(lastNames);
			book.setAuthorLastNames(authorLastNames);
			
			List<String> characterFirstNames = new ArrayList<String>();
			characterFirstNames.add(characterFirst);
			book.setCharacterFirstNames(characterFirstNames);
			
			List<String> characterLastNames = new ArrayList<String>();
			characterLastNames.add(characterLast);
			book.setCharacterLastNames(characterLastNames);
			
			List<String> genres = new ArrayList<String>();
			genres.add(genre);
			book.setGenres(genres);
			
			bookDAO.saveBook(book);
			return "/addBook";
			
		}
}

