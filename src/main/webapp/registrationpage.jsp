<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<link rel="stylesheet" href="CSS/registrationStyle.css">
<script type="text/javascript" src='JS/registrationvalidation.js'></script>
</head>
<body>
<div class="registercontainer">
<div class="adj">
<div class="register"> 
		<form name="frm" id="frm" action="registeruser" method="POST" onsubmit="return validateForm()" >
			<div class="logo">Register Here</div> 
			<input type="text" name="name" id="name" placeholder="First and surname" required><br>
			<div class="msg" id="namemsg"></div><br>
			<input type="email" name="email" id="email" onkeyup="checkemail(this.value)" placeholder="Email" required><br> 
			<div id="emailGrid">
			<div class="msg" id="emailmsg"></div><br>
			</div>
			<input type="text" name="username" id="username" onkeyup="checkusername(this.value)" placeholder="User name"><br>
			<div id="usernameGrid">
			<div class="msg" id="usernamemsg"></div><br>
			</div>
			<input type="password" name="password" id="password" placeholder="Password" required><br>
			<div class="msg" id="passwordmsg"></div><br>
			<div class="g-recaptcha" id="recapt" data-sitekey="6Lca4SQqAAAAAMR1uYXhG2V1o-yIw-dP3X3rZkyF"></div>
			<button type="submit" name="s" value="register" >Register</button><br>
			<div class="next">
			<a id="regtag" href="loginpage.jsp">already register?</a>
			</div>
		</form>
	</div>  <!-- register -->
	</div> <!-- adj -->
</div><!-- registercontainer -->	
</body>
</html>