package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.service.LikeCommentService;


@WebServlet("/likeforyoucontroller")
public class LikeForYouController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int postid=Integer.parseInt(request.getParameter("postid"));		
			// access user id from session
		HttpSession session=request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		LikeCommentService lcSer = new LikeCommentService();
		boolean result=lcSer.isAddLike(postid, userID);
		if(result) {
			// get count of like
			LikeCommentService se = new LikeCommentService();
			int likeCount=se.fetchLikeCount(postid);
			// check like 
            LikeCommentService lk = new LikeCommentService();
            int v=lk.checkLike(postid,userID);
			 if(v>0){
				 out.println("<a id='liked' onclick='unlikeForYou("+postid +")'> <i class='fa-solid fa-heart'></i>&nbsp"+likeCount+"</a>");
             }else{
				out.println("<a id='like'  onclick='likeForYou("+postid +")'> <i class='fa-solid fa-heart'></i>&nbsp"+ likeCount+"</a>");
			 }
         
		}
	}

}
