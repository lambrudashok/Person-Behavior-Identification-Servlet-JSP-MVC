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

import org.model.LoginModel;
import org.repository.DBHelper;
import org.service.ValidateAdminService;
import org.service.ValidateUserService;


@WebServlet("/validation")
public class validationServeletControllor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
  	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path=request.getServletContext().getRealPath("/")+"resources\\db.properties";
		DBHelper.path=path;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		LoginModel model = new LoginModel();
		model.setUsername(username);
		model.setPassword(password);
	
		ValidateAdminService adminSer = new ValidateAdminService();
		int adminID = adminSer.checkAdminLogin(model);
		
		if(adminID!=-1) {
			// Admin logics
			RequestDispatcher r = request.getRequestDispatcher("adminpage.jsp");
			r.forward(request, response);
			
		}else {
			// user logics
			ValidateUserService userSer = new ValidateUserService();         
			int userID = userSer.checkUserLogin(model);
			if(userID!=-1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userID", userID);
				
				model.setLoginid(userID);
				
				boolean res=userSer.isAddUserLogin(model); // add user login details
				
				RequestDispatcher r = request.getRequestDispatcher("userhomepage.jsp");
				r.forward(request, response);
			
			}else {
				
				RequestDispatcher r = request.getRequestDispatcher("loginpage.jsp");
				r.forward(request, response);
				
			}
		}
	}

}
