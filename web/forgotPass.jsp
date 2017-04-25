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
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<style>
			body { 
				background: url(loginbg.jpg) no-repeat center center fixed; 
				-webkit-background-size: cover;
				-moz-background-size: cover;
				-o-background-size: cover;
				background-size: cover;
				height:100%;
				width:100%;
			}
		</style>

		<title>Forgot password</title>
	</head>
	<body>
		<div class="container-fluid">
			<font face="roboto">
				<h1 align="center">Forgot Password</h1>
			</font>
			<!--NO ACTION YET-->

				<s:form action="forgotPassword" theme="bootstrap" cssClass="form">

				<!--EMPTY PA YUNG LIST-->
				<br>
				<center>
                                    <h3>Secret Question: </h3>
                                    <s:hidden name="username" value="%{#session.tempUser.username}"/>
                                    Username : <s:property value="#session.tempUser.username"/></br>
                                    Question : <s:property value="#session.tempUser.securityQuestion"/></br>
                                    <s:password name="securityAnswer" placeholder="Enter Answer Here" />
                                    <s:submit cssClass="btn btn-primary" value="Submit" />
                                    <s:actionerror/>
				</center>
				</s:form>



		</div>
	</body>
</html>
