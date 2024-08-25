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
import java.util.List;

import org.model.PostLayoutModel;
import org.model.PostModel;
import org.service.CreateUserPostService;
import org.service.LikeCommentService;

@WebServlet("/anotheruserprofile")
public class AnotherUserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		int userID=Integer.parseInt(session.getAttribute("userID").toString());
		
		int registerid=Integer.parseInt(request.getParameter("id"));
		int postid=Integer.parseInt(request.getParameter("btn"));
		String comment=(String)request.getParameter("comment");
		
		PostModel pmodel = new PostModel();
		pmodel.setComment(comment);
		pmodel.setPostid(postid);
		pmodel.setRegisterid(userID);
		
		LikeCommentService likecomSer = new LikeCommentService();
		boolean commentResult=likecomSer.isAddComment(pmodel);
		if(commentResult) {
			RequestDispatcher r = request.getRequestDispatcher("anotheruserprofilepage.jsp?id="+registerid);
			r.forward(request, response);
		}
		  	
	}

}
