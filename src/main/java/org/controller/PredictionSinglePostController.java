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

import org.model.PredictionBehaviorModel;
import org.service.PredictionAdminService;


@WebServlet("/predictionsinglepost")
public class PredictionSinglePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int postid=Integer.parseInt(request.getParameter("postid"));
		
		// prediction person using post
		PredictionAdminService predictSer = new PredictionAdminService();
		String post=predictSer.ViewPost(postid);
		
		// fetch person behavior single post prediction 
		PredictionBehaviorModel predict=predictSer.predictPersonBehavior(post);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("openessToExperience", predict.getOpenessToExperience()); 
		session.setAttribute("conscientiousness",predict.getConscientiousness());
		session.setAttribute("extroversion", predict.getExtroversion());
		session.setAttribute("agreeableness", predict.getAgreeableness());
		session.setAttribute("neuroticism", predict.getNeuroticism());
		
		RequestDispatcher r = request.getRequestDispatcher("predictionchartpage.jsp");
		r.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
