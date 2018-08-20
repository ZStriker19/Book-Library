package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class ForumController {


	@Autowired
	private BookDAO bookDAO;
	
	@RequestMapping(path="/users/forum")
	public String displayForum(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		return "forum";
	}
}