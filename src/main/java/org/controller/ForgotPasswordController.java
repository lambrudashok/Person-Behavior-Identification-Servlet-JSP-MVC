package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.repository.DBHelper;
import org.service.ChangePasswordService;


@WebServlet("/forgotpassword")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int registerid = Integer.parseInt(session.getAttribute("forgotregisterid").toString());
		
		String pass=(String)request.getParameter("pass");
		
		ChangePasswordService chSer = new ChangePasswordService();
		int result = chSer.changeUserPassword(pass, registerid);
			
		out.println("<h3>Forgot Password</h3>");
		out.println("<input type='text' name='username' id='username' placeholder='Enter Username' required><br>");
		out.println("<input type='text' name='newpass' id='newpass' placeholder='New Password' required><br>");
		out.println("<input type='text' name='retypepass' id='retypepass' placeholder='Retype New Password' required><br>");
		out.println("<div id='msg' class='msg'style='color:green; font-weight:20px;' >Password forgot successfully</div>");
		out.println("<button type='submit' name='forgot' id='forgot' onclick='return checkPassword()' >Forgot Password</button>");
		
	}

}
