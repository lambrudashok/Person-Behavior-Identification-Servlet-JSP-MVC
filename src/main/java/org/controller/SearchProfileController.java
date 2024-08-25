package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.model.UserInfoModel;
import org.service.UserSearchService;

@WebServlet("/searchprofile")
public class SearchProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("n");
		
		UserSearchService searchSer = new UserSearchService();
		List <UserInfoModel> list = searchSer.fetchAllUserDetails(name);
		
		if(list!=null){
			for(UserInfoModel userInfo:list){
				out.println("<a class='userappinfo' id='userappinfo' href='anotheruserprofilepage.jsp?id="+userInfo.getRegisterid()+"' >"); 
				out.println("<div class='photo'>");
				out.println("<img alt='' src='Profile_Images/"+userInfo.getProfileimage()+"'>");
				out.println("</div>");  //photo
				out.println("<div class='userdetails'>");
				out.println("<div class='namediv'>");
				out.println("<div id='name'>"+userInfo.getName()+"</div>");
				out.println("<div id='username'>"+userInfo.getUsername()+"</div>");
				out.println("</div>");
				out.println("</div>"); // userdetails 
				out.println("</a>");  // userappinfo
			}
		}else{
			 out.println("<h4>User Not Found</h4>");
		}
	}

}
