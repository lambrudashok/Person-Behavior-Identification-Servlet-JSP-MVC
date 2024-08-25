package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.service.FollowingService;


@WebServlet("/userfollowing")
public class FollowingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		int followingUserID = Integer.parseInt(request.getParameter("following"));
		
		FollowingService followSer = new FollowingService();
		int result=followSer.removeFollowingUser(followingUserID, registerId);
		if(result!=0) {
			RequestDispatcher r = request.getRequestDispatcher("followingpage.jsp");
			r.forward(request, response);
		}
		
	}

}
