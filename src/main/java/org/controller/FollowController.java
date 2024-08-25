package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.service.FollowingService;


@WebServlet("/follow")
public class FollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int followid=Integer.parseInt(request.getParameter("followid"));
		
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		FollowingService followSer = new FollowingService();
		boolean result=followSer.isAddFollowingUser(userID,followid);
		
		
		
		FollowingService fs = new FollowingService();
		int status=fs.checkFollowingStatus(followid,userID);
		if(status==0){
			// follow btn
			out.println("<button name='follow' id='follow' value='"+followid+"' onclick='followUser(this.value)' >Follow</button>");            
		}else{
			 // following btn 
			out.println("<button name='following' id='following' value='"+followid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>");            
		}
		
		
	}

}
