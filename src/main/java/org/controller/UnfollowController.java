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


@WebServlet("/unfollow")
public class UnfollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int unfollowid=Integer.parseInt(request.getParameter("unfollowid"));
		
		HttpSession session = request.getSession(false);
		int registerId = Integer.parseInt(session.getAttribute("userID").toString());
		
		
		FollowingService followSer = new FollowingService();
		int result=followSer.removeFollowingUser(unfollowid, registerId);
		
		FollowingService fs = new FollowingService();
		int status=fs.checkFollowingStatus(unfollowid,registerId);
		if(status==0){
			// follow btn
			out.println("<button name='follow' id='follow' value='"+unfollowid+"' onclick='followUser(this.value)' >Follow</button>");            
		}else{
			 // following btn 
			out.println("<button name='following' id='following' value='"+unfollowid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>");            
		}
		
		
	}

}
