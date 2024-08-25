package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.service.CreateUserPostService;


@WebServlet("/deletepost")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int postid=Integer.parseInt(request.getParameter("postid"));
		CreateUserPostService delpostSer = new CreateUserPostService();
		int deletepost=delpostSer.deletePost(postid);
		if(deletepost!=0) {
			RequestDispatcher r = request.getRequestDispatcher("profilepage.jsp");
			r.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
