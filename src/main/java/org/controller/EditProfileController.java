package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.model.UserInfoModel;
import org.service.UserRegistrationService;


@WebServlet("/editprofile")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		String name=(String)request.getParameter("name");
		String username=(String)request.getParameter("username");
		String email=(String)request.getParameter("email");
		String bio=(String)request.getParameter("bio");
		
		HttpSession session = request.getSession(false);
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		
		UserRegistrationService se = new UserRegistrationService();
		UserInfoModel model = se.getUserInfo(userID);
		
		
		// declaration
		boolean usernameResult=false,emailResult=false,bioResult=false; 
		int done=0;
		String usernameError="",emailError="",allMsg="";
		
		UserRegistrationService regSer = new UserRegistrationService();
		if(!username.isEmpty()) {
			usernameResult=regSer.searchUsername(username);   // check username duplicate
			if(usernameResult) {
				// duplicate
				usernameError="username alredy taken by another user. ";
			}else {
				//update username
				done=regSer.isUpdateUsername(username, userID);
			}
		}
		
		if(!email.isEmpty()) {
			emailResult=regSer.searchEmail(email);  // check email duplicate
			if(!emailResult) {
				//update email
				done=regSer.isUpdateEmail(email, userID);
			}else {
				emailError="email already taken by another user. ";
			}
		}
		
		if(!name.isEmpty()) {
			done=regSer.isUpdateCustomerName(name, userID); // update customer name
		}
		
		if(!bio.isEmpty()) {
			int searchbio = regSer.searchBio(userID); // search bio
			if(searchbio!=0) {
				done=regSer.isUpdateBio(bio, searchbio);   // update bio
			}else {
				bioResult= regSer.isaddBio(bio, userID); // add bio
				allMsg="bio added successfully. ";
			}
		}
		
		if(done>0) {
			allMsg="updated successfully";
		}
		
		
		
		 
		out.println("<h3>Edit Profile</h3>");
		out.println("<div class='photo'>");
		out.println("<div class='image'>");
		out.println("<img alt='' src='Profile_Images/"+model.getProfileimage()+"'>");
		out.println("</div>");
		out.println("<div class='userdetail'>");
		out.println("<h4>"+model.getUsername()+"</h4>");
		out.println("<h4 id='name'>"+model.getName()+"</h4>");  
		out.println("</div>");
		out.println("<input type='file' class='pro' name='pro'  id='pro'><br>");
		out.println("</div>");
		out.println("<input type='text' name='cname' id='cname' placeholder='name'><br>");
		out.println("<div id='msgname' class='msg'></div>");
		out.println("<input type='text' name='username' id='username' placeholder='Username'><br>");
		out.println("<div id='msgusername' class='msg' style='color:red;font-size:20px;'>"+usernameError+"</div>");
		out.println("<input type='text' name='email' id='email' placeholder='Email'><br>");
		out.println("<div id='msgemail' class='msg' style='color:red;font-size:20px;'>"+emailError+"</div>");
		out.println("<input type='text' name='bio' id='bio' placeholder='Bio'><br>");
		out.println("<div id='msg' class='msg' style='color:green;font-size:20px;'>"+allMsg+"</div>");
		out.println("<button type='submit' name='editbtn' id='editbtn' onclick='return checkField()' >Save</button>");
		
	}

}
