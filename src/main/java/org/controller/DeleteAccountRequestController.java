package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.model.RegistrationModel;
import org.service.ValidateAdminService;


@WebServlet("/deleteaccountrequest")
public class DeleteAccountRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int userid=Integer.parseInt(request.getParameter("userid"));
		
		ValidateAdminService ser = new ValidateAdminService();
		int result=ser.deleteUserRequestAccount(userid);
		
		List<RegistrationModel> list =ser.fetchDeleteUserAccountReuests();
		if(list!=null){
			
			out.println("<h2>User Requests Found</h2>");
			out.println("<div class='columnname'>");
			out.println("<div id='columnid'>RegisterId</div>");
			out.println("<div id='columnphoto'>Profile Photo</div>");
			out.println("<div id='columnnam'>Name</div>");
			out.println("<div id='columnusername'>UserName</div>");
			out.println("<div id='columnarrive'>Arrive Date</div>");
			out.println("<div id='columnremaining'>Remaining Days</div>");
			out.println("<div id='columnother'>Others</div>");
			out.println("</div>");//columname
			
			for(RegistrationModel info:list){
			
				out.println("<div class='details'>");
				out.println("<div id='userid'>"+info.getRegisterid()+"</div>");
				out.println("<div id='photo'><img alt='' src='Profile_Images/"+info.getProfileimgname()+"'></div>");
				out.println("<div id='cname'>"+info.getCustomername()+"</div>");
				out.println("<div id='username'>"+info.getUsername()+"</div>");
				out.println("<div id='arivedate'>"+info.getDate()+"</div>");
				out.println("<div id='remainingdays'>days : "+info.getRemain()+"</div>");
				out.println("<div id='delete'><a onclick='deleteAccountRequestUser("+info.getRegisterid()+")'>Confirm</a></div>");
				out.println("</div>");// details
			}
		}else{
			out.println("<h2>Account Delete Requests Not Found</h2>");
		}
		
	}

}
