<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, org.model.*,org.service.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="CSS/predictionchartStyle.css">
<script type="text/javascript" src='JS/predictionchartvalidation.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>

<div class="chartcontainer">

<div class="chart">
<h1>Predicted Behavior</h1>

<%
session = request.getSession(false);

int openessToExperience=Integer.parseInt(session.getAttribute("openessToExperience").toString());
int conscientiousness=Integer.parseInt(session.getAttribute("conscientiousness").toString());
int extroversion=Integer.parseInt(session.getAttribute("extroversion").toString());
int agreeableness=Integer.parseInt(session.getAttribute("agreeableness").toString());
int neuroticism=Integer.parseInt(session.getAttribute("neuroticism").toString());

session.removeAttribute("openessToExperience");
session.removeAttribute("conscientiousness");
session.removeAttribute("extroversion");
session.removeAttribute("agreeableness");
session.removeAttribute("neuroticism");
%>
<div class="area">
<div>
  <canvas id="myChart"></canvas>
</div>
<script type="text/javascript">

const ctx = document.getElementById('myChart').getContext('2d');
new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ['Openess To Experience', 'Conscientiousness', 'Extroversion', 'Agreeableness', 'Neuroticism'],
    datasets: [{
      label: 'person behavior ratio',
      data: [<%=openessToExperience%>, <%=conscientiousness%>, <%=extroversion%>, <%=agreeableness%>, <%=neuroticism%>],
      backgroundColor:[
			
			'rgba(54,162,235,0.2)',
			'rgba(255,206,86,0.2)',
			'rgba(75,192,192,0.2)',
			'rgba(153,102,255,0.2)',
			'rgba(255,99,132,0.2)',
		],
		borderColor:[
			
			'rgba(54,162,235,1)',
			'rgba(255,206,86,1)',
			'rgba(75,192,192,1)',
			'rgba(153,102,255,1)',
			'rgba(255,99,132,1)',
		],
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});

</script>
</div> <!-- area -->
</div> <!-- chart -->
</div><!-- chartcontainer -->
</body>
</html>