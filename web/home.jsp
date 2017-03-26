<%-- 
		Document   : home
		Created on : 01 31, 17, 9:03:37 PM
		Author     : Aspire
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		
		<title>RADTECH PARS</title>
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
				<a href="welcome.jsp" class="navbar-brand">RADTECH PARS <span class="glyphicon glyphicon-home"></span></a>
			</div>

			<!-- Menu Items -->
			<div class="collapse navbar-collapse" id="mainNavBar">

				<ul class="nav navbar-nav">
					<li><a href="search.jsp">Search <span class="glyphicon glyphicon-search"></span></a></li>
					<li><a href="schedule.jsp">Schedule <span class="glyphicon glyphicon-calendar"></span></a></li>
						<s:url action="statistics" var="stat"/>
					<li><s:a href="statistics">Statistics <span class="glyphicon glyphicon-stats"></span></s:a></li>
						<li><a href="archives.jsp">Archives <span class="glyphicon glyphicon-trash"></span></a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
					<s:url action="fetchuser">
						<s:param name="username" value="%{#session.currentuser.username}"/>
					</s:url>
					<li><s:a href="fetchuser"><s:property value="%{#session.currentUser.name}"/> <span class="glyphicon glyphicon-cog"></span> </s:a></li>
						<s:url value="logout" var="logout"/>
					<li><s:a href="%{logout}">Logout <span class="glyphicon glyphicon-log-in"></span></s:a></li>
				</ul>        

			</div>
		</nav> 
		<div class="container-fluid">
			<br>
			<br>
			<br>
		</div>   
	</body>
</html>
