<%-- 
	Document   : forgotPass
	Created on : Mar 9, 2017, 9:43:52 PM
	Author     : Carl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

		<title>Forgot password</title>
	</head>
	<body>
		<div class="container">
			<h1>Forgot Password</h1>
		
			<!--NO ACTION YET-->
			
				<s:form action="" theme="bootstrap" cssClass="form">
				
				<!--EMPTY PA YUNG LIST-->
				
				
				<h3>Secret Question: </h3>
				<s:select name="secretQuestions" list="{'sample1', 'sample2'}" headerKey="-1" headerValue="Select Secret Question" />
				
				<s:password name="secretAnswer" placeholder="Enter Answer Here" />
				<s:submit cssClass="btn btn-primary" value="Submit" />
				</s:form>
		
		

		</div>
	</body>
</html>
