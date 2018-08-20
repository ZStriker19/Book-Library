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

import com.techelevator.model.book.Book;
import com.techelevator.model.book.BookDAO;
import com.techelevator.model.forum.Forum;
import com.techelevator.model.forum.ForumDAO;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class ForumController {


	@Autowired
	private ForumDAO forumDao;
	
	@RequestMapping(path="/users/forum")
	public String displayForum(HttpSession session, HttpServletRequest request) {
		List<Forum> posts = forumDao.getAllPosts();
		request.setAttribute("posts", posts);
		User currentUser = (User) session.getAttribute("currentUser");
		
		return "forum";
	}
}