package com.techelevator.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	
	@RequestMapping(path="/forum/newPost")
	public String createNewPost(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		
		return "newPost";
	}
	
	@RequestMapping(path="/forum/newPost", method=RequestMethod.POST)
	public String displayNewPostOnForum(HttpSession session, HttpServletRequest request) {
		User currentUser = (User) session.getAttribute("currentUser");
		long  appUserId = currentUser.getId();
		Forum post = createForumPost(request);
		
		
		forumDao.save(post, appUserId);
		return "redirect:/user/forum";
	}
	
	private Forum createForumPost(HttpServletRequest request) {
		Forum post = new Forum();
		Date curDate = new Date();
		String message = request.getParameter("message");
		String subject = request.getParameter("subject");
		
		post.setDatePosted(curDate);
		post.setMessage(message);
		post.setSubject(subject);
		
		return post;
		
	}
	

}