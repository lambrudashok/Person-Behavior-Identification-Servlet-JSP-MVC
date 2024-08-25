package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/forgotcheckotp")
public class ForgotCheckOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h3>Check your email to get your confirmation code</h3>");
		out.println("<input type='text' id='otpinput'  placeholder='Enter OTP code'><br>");
		out.println("<div id='msgotp' class='msg'>Invalid OTP. Please enter correct OTP</div>");
		out.println("<button type='submit' name='otpverifybtn' id='otpverifybtn'  >Confirm</button><br>");
		
	}

}
