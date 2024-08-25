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
import org.model.RegistrationModel;
import org.service.CreateUserPostService;
import org.service.UserRegistrationService;


@WebServlet("/updateprofilephoto")
@MultipartConfig
public class UpdateProfilePhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		  
		Part p = request.getPart("chooseprofile"); // fetch profile image
		String fileName=p.getSubmittedFileName();  // fetch profile image name
		  
		// access user id from session
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		  
		RegistrationModel model = new RegistrationModel();
		model.setProfileimgname(fileName); //set image name
		model.setRegisterid(userID);
		
		UserRegistrationService uSer = new UserRegistrationService();
		boolean profileResult=uSer.isAddProfilePhoto(model);
		 
		if(profileResult) {
			 // upload photo in server (server as a folder)
			 String path = getServletContext().getRealPath("") + "Profile_Images";
			 File f = new File(path);
			 p.write(path + File.separator +fileName);
			 
			 RequestDispatcher r =request.getRequestDispatcher("editprofilepage.jsp");
			 r.forward(request, response); 
		}else {
			 RequestDispatcher r =request.getRequestDispatcher("editprofilepage.jsp");
			 r.forward(request, response);  
	     } 
	 
	}

}
