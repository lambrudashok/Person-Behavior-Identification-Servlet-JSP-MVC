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
import java.util.List;

import org.model.UserInfoModel;
import org.service.FollowingService;
import org.service.UserSearchService;

@WebServlet("/searchuser")
public class SearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("n");
		
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		
		UserSearchService searchSer = new UserSearchService();
		List<UserInfoModel> list= searchSer.fetchAllUserDetails(name,userID);	
		
		if(list!=null){
			for(UserInfoModel userInfo:list){
				out.println("<div class='userfollowing'>");
				out.println("<div class='photo'>");
				out.println("<img alt='' src='Profile_Images/"+userInfo.getProfileimage()+"'>");
				out.println("</div>"); // photo 	
				out.println("<div class='userdetails'>");
				out.println("<div class='namediv'>");
				out.println("<div id='name'>"+userInfo.getName()+"</div>");
				out.println("<div id='username'>"+userInfo.getUsername()+"</div>");
				out.println("</div>");
				out.println("<div id='btndiv'>");
				
				if(userInfo.getStatus()==0){
						//follow btn
				out.println("<button name='follow' id='follow' value='"+userInfo.getRegisterid()+"' onclick='followUser(this.value)' >Follow</button>");            
				}else{
						//following btn 
				out.println("<button name='following' id='following' value='"+userInfo.getRegisterid()+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>");            
				}				
				out.println("</div>");
				out.println("</div>"); // userdetails 
				out.println("</div>"); // userfollowing 	
			}
		}else{
			out.println("<h4>User Not Found</h4>");
		}
	}

}
