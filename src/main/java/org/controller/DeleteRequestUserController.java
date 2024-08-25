package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.service.UserRegistrationService;


@WebServlet("/deleterequest")
public class DeleteRequestUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int userID=Integer.parseInt(request.getParameter("userID"));
		UserRegistrationService userSer=new UserRegistrationService();
		int delete=userSer.deleteUserAccount(userID);
		
		int checkdelete=userSer.checkRequestDelete(userID);
		if(checkdelete!=0){
			out.println("<h2>Recover Account ?</h2>");
			out.println("<div class='btndiv'>");
			out.println("<button type='submit' id='yesbtn' onclick='recoverRequest("+userID+")' >Yes</button>");
			out.println("<form name='frm' action='userhomepage.jsp' method='post'>");
			out.println("<button type='submit' id='nobtn' >No</button>");
			out.println("</form>");
			out.println("</div>");
		}else{
			out.println("<h2>Do You Want Delete Account ?</h2>");
			out.println("<div class='btndiv'>");
			out.println("<button type='submit' id='yesbtn' onclick='deleteRequest("+userID+")'>Yes</button>");
			out.println("<form name='frm' action='userhomepage.jsp' method='post'>");
			out.println("<button type='submit' id='nobtn' >No</button>");
			out.println("</form>");
			out.println("</div>");	
		}
		
		
	}

}
