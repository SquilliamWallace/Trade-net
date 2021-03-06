package edu.ycp.cs320.Trade_net.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Trade_net.controller.PostController;
import edu.ycp.cs320.Trade_net.model.Posts;
import edu.ycp.cs320.Trade_net.model.User;

public class ListingsServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		//if the user is not logged in send to login page
		if (user == null){
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		//if the user is logged in send to listings page
		else{
			req.getRequestDispatcher("/_view/listings.jsp").forward(req, resp);

		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String platform = req.getParameter("platform");
		String game = req.getParameter("game");
		String type = req.getParameter("type");
		/*
		 * Send a query recieving posts that satisfy the above conditions
		 * Create 10 posts with the queried information
		 * Send these posts to the jsp
		 * Store position in the list of queries so that a next button can be implemented that prints the next 10 posts
		 */
		System.out.println(platform);
		System.out.println(game);
		System.out.println(type);
		
		Posts post = new Posts();
		post.setPlatform(platform);
		post.setGame(game);
		post.setBuy(type);
		post.setTime(1);
		post.setUserId(1);
		post.setMessage("message");
		PostController controller = new PostController(post);
		
		List<Posts> list =controller.getPosts();
		
		req.setAttribute("list", list);
		
		
		req.getRequestDispatcher("/_view/listings.jsp").forward(req, resp);

		
		
	}

}
