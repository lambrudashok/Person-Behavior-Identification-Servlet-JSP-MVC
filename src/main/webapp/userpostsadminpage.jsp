<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="org.model.*,org.service.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/userpostsadminStyle.css">
</head>
<body>
<div class="postcontainer">

<div class="main">

<h2>Posts Details</h2>
<div class="columnname">
<div id="columnpostid">PostId</div>
<div id="columnusername">UserName</div>
<div id="columnpostimage">Post Image</div>
<div id="columnpost">Post</div>
</div> <!-- columnname -->

<div class="adjust">

<div id="postsGrid">

<%

	ValidateAdminService adSer = new ValidateAdminService(); 
	List<PostLayoutModel> list =adSer.ViewAllUserPosts();
	for(PostLayoutModel info:list){
		%>
	
		<div class="details">
		<div id="postid"><%=info.getPostid()%></div>
		<div id="username"><%=info.getUsername() %></div>
		<div id="postimage"><img alt="" src="Post_Images/<%=info.getImgname()%>"></div>
		<div id="post"><%=info.getPost()%></div>
		</div> <!-- details -->
	
	<%
	}
%>
</div>
</div>
</div> <!-- main -->
</div>  <!-- postcontainer -->
</body>
</html>