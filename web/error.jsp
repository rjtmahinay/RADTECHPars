<%-- 
	Document   : error
	Created on : 22-Mar-2017, 08:54:41
	Author     : Sphere
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<title>JSP Page</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<h1>An Error occured!
			Click <s:a href="welcome.jsp">here</s:a> to return home.
		</h1>

	</body>
</html>
