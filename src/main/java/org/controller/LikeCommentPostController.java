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

import org.model.PostModel;
import org.service.LikeCommentService;

@WebServlet("/likecomment")
public class LikeCommentPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		int postid=Integer.parseInt(request.getParameter("btn"));
		String comment=(String)request.getParameter("comment");
		
			// access user id from session
			HttpSession session=request.getSession(false);
			int userID = Integer.parseInt(session.getAttribute("userID").toString());
			
			PostModel pmodel = new PostModel();
			pmodel.setComment(comment);
			pmodel.setPostid(postid);
			pmodel.setRegisterid(userID);
			
			LikeCommentService likecomSer = new LikeCommentService();
			boolean commentResult=likecomSer.isAddComment(pmodel);
			if(commentResult) {
				RequestDispatcher r = request.getRequestDispatcher("profilepage.jsp");
				r.forward(request, response);
			}
		
	}

}
