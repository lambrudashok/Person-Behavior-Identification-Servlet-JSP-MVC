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
import org.service.UserRegistrationService;


@WebServlet("/forgotcheckusername")
public class ForgotCheckUsernameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// set path of connection
		String path=request.getServletContext().getRealPath("/")+"resources\\db.properties";
		DBHelper.path=path;
		
		String username=(String)request.getParameter("username");
		//check username valid or not
		ChangePasswordService uSer = new ChangePasswordService();
		int result=uSer.checkUsername(username);
		if(result!=0) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			
			out.println("<div class='emaildiv' style='display:block;' id='emaildiv'>");
			out.println("<h3>Confirm your email</h3>");
			out.println("<input type='email' id='email' placeholder='Enter Your Email'><br>");
			out.println("<div id='msgemail' class='msg'></div>");
			out.println("<button type='submit' name='emailconfirmbtn' id='emailconfirmbtn' onclick='checkConfirmEmail()' >Confirm</button><br>");
			out.println("<form action='loginpage.jsp' method='post'>");
			out.println("<button type='submit'  id='cancelbtn'>Cancel</button><br>");
			out.println("</form>");
			out.println("</div>");
		}else {
			out.println("<h3>Forgot Password</h3>");
			out.println("<input type='text' name='username' id='username' placeholder='Enter Username' required><br>");
			out.println("<div id='msgusername' class='msg'>Invalid username. Please try again</div>");
			out.println("<button type='submit' name='usernamenextbtn' id='usernamenextbtn' onclick='checkUsername()' >Next</button><br>");
			out.println("<form action='loginpage.jsp' method='post'>");
			out.println("<button type='submit' id='cancelbtn' >Cancel</button><br>");
			out.println("</form>");
		}
	}

}
