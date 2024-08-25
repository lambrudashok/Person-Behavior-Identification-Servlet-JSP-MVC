package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.service.ChangePasswordService;


@WebServlet("/forgotcheckemail")
public class ForgotCheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		
		String email=(String)request.getParameter("email");
		
		// check user email valid or not
		ChangePasswordService uSer = new ChangePasswordService();
		int emailResult = uSer.checkEmail(email, username);
		if(emailResult!=0) {
			session.setAttribute("forgotregisterid", emailResult);
			
			out.println("<div class='sendotpdiv' style='display:block;' id='sendotpdiv'>");
			out.println("<h3>Where should we send a confirmation code</h3>");
			out.println("<input type='hidden' value='"+email+"' id='email'><br>");
			out.println("<h5>Send an email to "+email+"</h5>");
			out.println("<button type='submit' name='sendotpbtn' id='sendotpbtn' onclick='checkOTP()'  >Send OTP</button><br>");
			out.println("</div>"); // sendotpdiv
			
		}else {
			out.println("<h3>Confirm your email</h3>");
			out.println("<input type='email' id='email' placeholder='Enter Your Email'><br>");
			out.println("<div id='msgemail' class='msg'>Invalid email. Please try again</div>");
			out.println("<button type='submit' name='emailconfirmbtn' id='emailconfirmbtn' onclick='checkConfirmEmail()' >Send OTP</button><br>");
			out.println("<form action='loginpage.jsp' method='post'>");
			out.println("<button type='submit'  id='cancelbtn'>Cancel</button><br>");
			out.println("</form>");
		}
	}

}
