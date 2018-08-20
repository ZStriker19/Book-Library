package com.techelevator.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.book.BookDAO;
import com.techelevator.model.user.User;

@Controller
@SessionAttributes("currentUser")
public class NewPostController {


	@Autowired
	private BookDAO bookDAO;
	
	@RequestMapping(path="/forum/newPost")
	public String createNewPost(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		return "newPost";
	}
	@RequestMapping(path="/forum/newPost", method=RequestMethod.POST)
	public String displayNewPostOnForum(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		return "redirect:/user/forum";
	}
}