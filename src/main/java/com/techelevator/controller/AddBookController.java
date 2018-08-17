package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		public String createNewBook(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
			Book book = createBook(request);
			
			bookDAO.saveBook(book);
			redirectAttributes.addFlashAttribute("addedBook", book);
			redirectAttributes.addFlashAttribute("message", book.getTitle() + " added successfully");

			return "redirect:/";
			
		}
		
		private Book createBook(HttpServletRequest request) {
			Book book = new Book();
			
			String title = (String) request.getParameter("title-input");
			String section = (String) request.getParameter("section-input");
			
			List<String> authorFirstNames = new ArrayList<String>();
			
			List<String> authorLastNames = new ArrayList<String>();
			
			List<String> characterFirstNames = new ArrayList<String>();
			
			List<String> characterLastNames = new ArrayList<String>();
			
			List<String> genres = new ArrayList<String>();
			
			
			String genre1 = (String) request.getParameter("genre-1");
			String genre2 = (String) request.getParameter("genre-2");
			String genre3 = (String) request.getParameter("genre-3");
			
			genres.add(genre1);
			genres.add(genre2);
			genres.add(genre3);
			
			
			
			
			
			String authorFName1 = (String) request.getParameter("author-1-first-name");
			String authorLName1 = (String) request.getParameter("author-1-last-name");
			
			String authorFName2 = (String) request.getParameter("author-2-first-name");
			String authorLName2 = (String) request.getParameter("author-2-last-name");
			
			String authorFName3 = (String) request.getParameter("author-3-first-name");
			String authorLName3 = (String) request.getParameter("author-3-last-name");
			
			String authorFName4 = (String) request.getParameter("author-4-first-name");
			String authorLName4 = (String) request.getParameter("author-4-last-name");
			
			
			
			
			String characterFName1 = (String) request.getParameter("character-1-first-name");
			String characterLName1 = (String) request.getParameter("character-1-last-name");
			
			String characterFName2 = (String) request.getParameter("character-2-first-name");
			String characterLName2 = (String) request.getParameter("character-2-last-name");
			
			String characterFName3 = (String) request.getParameter("character-3-first-name");
			String characterLName3 = (String) request.getParameter("character-3-last-name");
			
			String characterFName4 = (String) request.getParameter("character-4-first-name");
			String characterLName4 = (String) request.getParameter("character-4-last-name");
			
			String characterFName5 = (String) request.getParameter("character-5-first-name");
			String characterLName5 = (String) request.getParameter("character-5-last-name");
			
			String characterFName6 = (String) request.getParameter("character-6-first-name");
			String characterLName6 = (String) request.getParameter("character-6-last-name");
			
			
			
			authorFirstNames.add(authorFName1);
			authorFirstNames.add(authorFName2);
			authorFirstNames.add(authorFName3);
			authorFirstNames.add(authorFName4);
			
			authorLastNames.add(authorLName1);
			authorLastNames.add(authorLName2);
			authorLastNames.add(authorLName3);
			authorLastNames.add(authorLName4);
			
			characterFirstNames.add(characterFName1);
			characterFirstNames.add(characterFName2);
			characterFirstNames.add(characterFName3);
			characterFirstNames.add(characterFName4);
			characterFirstNames.add(characterFName5);
			characterFirstNames.add(characterFName6);
			
			characterLastNames.add(characterLName1);
			characterLastNames.add(characterLName2);
			characterLastNames.add(characterLName3);
			characterLastNames.add(characterLName4);
			characterLastNames.add(characterLName5);
			characterLastNames.add(characterLName6);
			
			
			authorFirstNames = authorFirstNames.stream().filter(item-> !item.equals("")).collect(Collectors.toList());
			authorLastNames =  authorLastNames.stream().filter(item-> !item.equals("")).collect(Collectors.toList());
			characterFirstNames = characterFirstNames.stream().filter(item -> !item.equals("")).collect(Collectors.toList());
			characterLastNames = characterLastNames.stream().filter(item -> !item.equals("")).collect(Collectors.toList());
			genres = genres.stream().filter(item -> !item.equals("")).collect(Collectors.toList());
			
			
			book.setAuthorFirstNames(authorFirstNames);
			book.setAuthorLastNames(authorLastNames);
			book.setCharacterFirstNames(characterFirstNames);
			book.setCharacterLastNames(characterLastNames);
			book.setGenres(genres);
			book.setSection(section);
			book.setTitle(title);
			
			for (String lName : characterLastNames) {
				System.out.println("last names " + lName);
			}
			
			for (String fName : characterFirstNames) {
				System.out.println("first names " + fName);
			}
			
			
			return book;
			
		}
		
		
}

