package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.RegistrationModel;
import org.repository.DBHelper;
import org.service.UserRegistrationService;

@WebServlet("/registeruser")
public class RegistrationUserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password =request.getParameter("password");
		
		UserRegistrationService regSer = new UserRegistrationService();		
		
		RegistrationModel model = new RegistrationModel();
		model.setCustomername(name);
		model.setEmail(email);
		model.setUsername(username);
		model.setPassword(password);
		boolean register=regSer.isAddNewUserRegistration(model);
		if(register) {
			RequestDispatcher r = request.getRequestDispatcher("loginpage.jsp");
			r.forward(request, response);
			
		}else {
			RequestDispatcher r = request.getRequestDispatcher("registrationpage.jsp");
			r.forward(request, response);
		}
	}
	

}
