<%-- 
	Document   : index
	Created on : 01 31, 17, 1:57:06 PM
	Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

		<title>RADTECH PARS</title>
	</head>
	<body background="cat1.jpg">

		<div class="container">
			<div class="row">
				<div class="col-md-9 col-sm-0">
					<br><br><br><br>
					<h1>Animal Station And Veterinary Clinic</h1>
					<h2>by RadTech</h2>
				</div>
				<div class="col-md-3 col-sm-12">
					<br><br><br><br>

					<div class="panel panel-default">
						<div class="panel-heading">Enter login information below</div>
						<div class="panel-body">
							<div id="actionTabs" class="container">
								<s:actionerror/>
								<s:form action="login" theme="bootstrap" cssClass="form">

									<br/>
									<div class="form-group">
										<label for="username">Username</label>
										<s:textfield name="username" placeholder="Username" class="form-control" /></div>
									<div class="form-group">
										<label for="password">Password</label>
										<s:password name="password" placeholder="Password" class="form-control" /></div>
									<s:submit cssClass="btn btn-primary " value="submit" />
								</s:form>
								
							</div>
								
								<!end of panel body->
						</div>                  <!end of panel->                
					</div>                  <!end of column-> 
				</div>                      <!end of row->
			</div>                          <!end of container->
		</div>
	</body>
</html>
