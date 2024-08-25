<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.service.*,org.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/logindetailsadminStyle.css">
</head>
<body>



<div class="detailscontainer">

<div class="main">



<div class="adjust">
	
	<%
	
		ValidateAdminService adSer = new ValidateAdminService(); 
		List<LoginModel> list =adSer.viewUserLoginDetails();
		
		if(list!=null){
			
			%>
			<h2>Users Login Details</h2>
			<div class="columnname">
			<div id="columnid">RegisterId</div>
			<div id="columnusername">UserName</div>
			<div id="columnpassword">Password</div>
			<div id="columndate">Date</div>
			<div id="columtime">Time</div>
			</div> <!-- columname -->
			<%
			
			for(LoginModel info:list){
				%>
			
				<div class="details">
				<div id="userid"><%=info.getLoginid()%></div>
				<div id="username"><%=info.getUsername() %></div>
				<div id="password"><%=info.getPassword()%></div>
				<div id="date"><%=info.getDate()%></div>
				<div id="time"><%=info.getTime() %></div>
				</div> <!-- details -->
				<%
			}
		}else{
			%>
			<h2>Login Details Not Found</h2>
			<%
		}
		
	%>
	</div>	

</div>	<!-- main -->

</div>  <!-- detailscontainer -->



</body>
</html>