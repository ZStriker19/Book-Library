package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.model.BookDAO;



@Controller
public class AddBookController {
//	
//		@Autowired
//		private BookDAO bookDAO;
//		
		
		@RequestMapping(path=("addBook"), method=RequestMethod.GET)
		public String showAddBookPage() {
		return "/addBook";
		}
}

