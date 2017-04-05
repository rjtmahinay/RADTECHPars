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

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
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
				<a href="schedule.jsp" class="navbar-brand">Animal Station <span class="glyphicon glyphicon-home"></span></a>
			</div>

			<!-- Menu Items -->
			<div class="collapse navbar-collapse" id="mainNavBar">
				
				<ul class="nav navbar-nav">
					<s:if test="%{#session.currentUser.name!='admin'}">
					<li><a href="search.jsp">Appointment <span class="glyphicon glyphicon-plus"></span></a></li>
					<li><a href="add.jsp">New Customer <span class="glyphicon glyphicon-user"></span></a></li>
					
						<li><s:a href="statistics">Statistics <span class="glyphicon glyphicon-stats"></span></s:a></li>
						<li><a href="archives.jsp">Archives <span class="glyphicon glyphicon-trash"></span></a></li>
					</s:if>	
					
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
			<div class="modal fade" id="addRecord2">
				<div class="modal-dialog">
					<div class="modal-content">
						<center>

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">Add Record</h3>
							</div>

							<!-INSERT FORM ACTION HERE->


							<s:form action="addRecord" theme="bootstrap" cssClass="form" method="POST">
								<div class="modal-body">


									<div class="table">
									<table class="table table-condensed">
										<tr>
											<td><b>Owner Name:</b></td>
											<td><s:textfield name="ownerName" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Address:</b></td>
											<td><s:textfield name="address" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Contact Number:</b></td>
											<td><s:textfield name="contactNumber" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Pet Name:</b></td>
											<td><s:textfield name="patientName" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Sex:</b></td>
											<td><s:select cssClass="text text-block" name="sex" list="{'', 'Male','Female'}" headerKey="-1"/></td>
										</tr>

										<tr>
											<td><b>Breed:</b></td>
											<td><s:textfield name="breed" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Color:</b></td>
											<td><s:textfield name="color" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Date of birth:</b></td>
											<td>
																								<s:textfield name="dateinput" id="datepicker" />
											</td>
										</tr>



										<tr>
											<td><b>Weight:</b></td>
											<td><s:textfield name="weight" placeholder=""/></td>
										</tr>

									</table>
								</div>






									<div class="modal-footer form-group" >
										<center>
											<s:submit cssClass="btn btn-primary btn-block" value="submit" />
											<s:reset value="clear" cssClass="btn btn-secondary btn-block"/>
										</center>
									</div>
								</div>
							</s:form>
						</center>  
					</div>
				</div>
			</div>
			<br>
			<br>
			<br>
		</div>   
	</body>
</html>
