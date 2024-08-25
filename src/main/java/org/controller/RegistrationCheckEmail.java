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


@WebServlet("/registercheckemail")
public class RegistrationCheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path=request.getServletContext().getRealPath("/")+"resources\\db.properties";
		DBHelper.path=path;
		String email=request.getParameter("email");
		
		UserRegistrationService regSer = new UserRegistrationService();		
		boolean  emailVal=regSer.searchEmail(email);  //check email duplicate
		
		if(emailVal) {
			out.println("<div class='msg' id='emailmsg'>Email already taken. Please choose another</div><br>");	 
		
		}else {
			out.println("<div class='msg' id='emailmsg'></div><br>");	 
		}
		
	}

}
