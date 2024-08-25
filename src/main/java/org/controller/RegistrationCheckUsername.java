package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.repository.DBHelper;
import org.service.UserRegistrationService;


@WebServlet("/registercheckusername")
public class RegistrationCheckUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path=request.getServletContext().getRealPath("/")+"resources\\db.properties";
		DBHelper.path=path;
		
		String username=request.getParameter("username");
		
		UserRegistrationService regSer = new UserRegistrationService();		
		
		boolean usernameVal=regSer.searchUsername(username);  // check username duplicate
		
		if(usernameVal) {
			out.println("<div class='msg' id='usernamemsg'>Username already taken. Please choose another</div><br>");
		}else {
			out.println("<div class='msg' id='usernamemsg'></div><br>");	 
		}
	}

}
