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

import org.model.PredictionBehaviorModel;
import org.model.PredictionModel;
import org.service.PredictionAdminService;

@WebServlet("/predictionoverall")
public class PredictionOverAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int registerId=Integer.parseInt(request.getParameter("overallpredictionbtn"));
		
		// OverAll prediction logic
		// posts , comments, like of post
		PredictionAdminService predictSer = new PredictionAdminService();
		
		String[] unlabelledInformation=predictSer.getAllPostsCommentsLikes(registerId);
		
		PredictionBehaviorModel predict=predictSer.predictOverAllPersonBehavior(unlabelledInformation);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("openessToExperience", predict.getOpenessToExperience()); 
		session.setAttribute("conscientiousness",predict.getConscientiousness());
		session.setAttribute("extroversion", predict.getExtroversion());
		session.setAttribute("agreeableness", predict.getAgreeableness());
		session.setAttribute("neuroticism", predict.getNeuroticism());
		
		RequestDispatcher r = request.getRequestDispatcher("predictionchartpage.jsp");
		r.forward(request, response);
		
		
		
		
		
		
	}

}
