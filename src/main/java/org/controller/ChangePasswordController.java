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

import org.service.ChangePasswordService;


@WebServlet("/changepassword")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		
		String currentPass =request.getParameter("curpass");
		String newPass = request.getParameter("newpass");
		
		ChangePasswordService changeSer = new ChangePasswordService();
		int result=changeSer.checkPassword(currentPass, userID);
		if(result>0) {
			ChangePasswordService chSer = new ChangePasswordService();
			int change=chSer.changeUserPassword(newPass, userID);
			RequestDispatcher r = request.getRequestDispatcher("changepasswordpage.jsp");
			r.forward(request, response);
		}else {
			RequestDispatcher r = request.getRequestDispatcher("changepasswordpage.jsp");
			r.forward(request, response);
		}
		
		
	}

}
