package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.model.RegistrationModel;
import org.service.ValidateAdminService;


@WebServlet("/userdeleteadmin")
public class UserDeleteAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		ValidateAdminService adSer = new ValidateAdminService();
		int result=adSer.deleteUser(userid);
		
			List<RegistrationModel> list =adSer.fetchAllUserDetails();
			for(RegistrationModel info:list){
			out.println("<div class='details'>");
			out.println("<div id='userid'>"+info.getRegisterid()+"</div>");
			out.println("<div id='photo'><img alt='' src='Profile_Images/"+info.getProfileimgname()+"'></div>");
			out.println("<div id='cname'>"+info.getCustomername()+"</div>");
			out.println("<div id='email'>"+info.getEmail()+"</div>");
			out.println("<div id='username'>"+info.getUsername()+"</div>");
			out.println("<div id='password'>"+info.getPassword()+"</div>");
			out.println("<div id='delete'><a onclick='userDelete("+info.getRegisterid()+")'>Delete</a></div>");
			out.println("</div>"); //details

			}
		
	}

}
