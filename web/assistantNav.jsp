<%-- 
	Document   : assistantNav
	Created on : Apr 2, 2017, 8:57:57 AM
	Author     : Carl
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title></title>
	</head>
	<body>
		
		<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">


			<!-- Logo -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="#" class="navbar-brand">Animal Station <span class="glyphicon glyphicon-home"></span></a>
			</div>

			<!-- Menu Items -->
			<div class="collapse navbar-collapse" id="mainNavBar">

				<ul class="nav navbar-nav">
					
					
					<li><a href="assistantHome.jsp">Appointments <span class="glyphicon glyphicon-calendar"></span></a></li>
					<li><a href="search.jsp">Add Existing <span class="glyphicon glyphicon-plus"></span></a></li>
					<li><a href="add.jsp">Add New <span class="glyphicon glyphicon-plus"></span></a></li>
					
						
				</ul>
				<ul class="nav navbar-nav navbar-right">
					
					<s:url action="fetchuser" var="getUser">
						<s:param name="username" value="%{#session.currentuser.username}"/>
					</s:url>
					
					<li><s:a href="%{getUser}"> <s:property value="%{#session.currentUser.name}"/> <span class="glyphicon glyphicon-cog"></span> </s:a></li>
					<s:url action='logout' var="logout"/>
					<li><s:a href="%{logout}"> Logout <span class="glyphicon glyphicon-log-in"/></s:a></li>
				</ul>        
			</div>
		</nav>
		<div class="container-fluid">
			<br><br><br>
		</div>
	</body>
</html>
