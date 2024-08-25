package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.PostModel;
import org.service.CreateUserPostService;


@WebServlet("/postsubmit")
@MultipartConfig
public class CreatePostServeletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html"); 
		  PrintWriter out = response.getWriter();
		  
		  String post=request.getParameter("postname"); // fetch post
		  Part p = request.getPart("postimagefile"); // fetch image
		  String fileName=p.getSubmittedFileName();  // fetch image file name
		  
		  // access user id from session
		  HttpSession session = request.getSession(false);
		  int userID = Integer.parseInt(session.getAttribute("userID").toString());
		  
		  PostModel pmodel = new PostModel();
		  pmodel.setPost(post);
		  pmodel.setRegisterid(userID);
		  if(fileName.isEmpty()) {
			  // set default image name
			 pmodel.setImgname("person.png");
			 CreateUserPostService postSer = new CreateUserPostService();
			 boolean postresult = postSer.isaddUserNewPost(pmodel);
			 HttpSession sessionPost = request.getSession();
			 sessionPost.setAttribute("postMsg", "Post Upload Successful");
			 RequestDispatcher r =request.getRequestDispatcher("postcreatepage.jsp");
			 r.forward(request, response); 
		  }else {
			  // set user post image
			pmodel.setImgname(fileName); 
			CreateUserPostService postSer = new CreateUserPostService();
			 boolean postresult = postSer.isaddUserNewPost(pmodel);
			 if(postresult) {
				 // upload photo in server (server as a folder)
				 String path = getServletContext().getRealPath("") + "Post_Images";
				 File f = new File(path);
				 p.write(path + File.separator +fileName);
	
				 HttpSession sessionPost = request.getSession();
				 sessionPost.setAttribute("postMsg", "Post Upload Successful");
				 
				 RequestDispatcher r =request.getRequestDispatcher("postcreatepage.jsp");
				 r.forward(request, response); 
			 }
			 
		  }
		  
	}

}
