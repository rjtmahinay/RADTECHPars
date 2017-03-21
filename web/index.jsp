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
	<body background="dog.jpg" style="width:100%;height:100%;" alt="[]">
		<div class="container">
		

		
			<div class="row">
				<div class="col col-md-9">
					<br><br><br><br>
					<h1>Animal Station And Veterinary Clinic</h1>
					<h2>by RadTech</h2>
                                        <script>
                                            var password = '<s:property value="#session.tempPassword"/>';
                                            if(password) alert("temporary password is : " + password);
                                        </script>
				</div>
				<div class="col col-md-3">
					<br><br><br><br>
					
					<div class="panel panel-default">
					
						<div class="panel-heading"><center>Enter login information below</center></div>
						<div class="panel-body">
							<center>
								<s:actionerror/>
								<s:form action="login" theme="bootstrap" cssClass="form">

									<br>
									<div class="form-group">
										<label for="username">Username</label>
										<s:textfield name="username" placeholder="Username" class="form-control" />
									</div>
									<div class="form-group">
										<label for="password">Password</label>
										<s:password name="password" placeholder="Password" class="form-control" />
									</div>
									
									<s:submit cssClass="btn btn-primary btn-block" value="submit" />
								</s:form>
								<button type="button" class="btn btn-secondary btn-block" data-toggle="modal" data-target="#forgotPass" >Forgot password?</button>
							</center>
								<!end of panel body->
						</div>                  <!end of panel->                
					
					</div>
				
				
				
				</div>
			</div>
								
								
		
	</div>							
						
						
						
		<div class="modal fade" id="forgotPass">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">

					<center>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="modal-title">Forgot Password</h3>
					</div>
					<s:form action="forgotPassword" theme="bootstrap" cssClass="form" method="POST">

						<div class="modal-body">
							<div class="form-group">
								<s:textfield label="Username" name="username" class="form-control" />
							</div>


							<div class="modal-footer form-group" >

								<s:submit cssClass="btn btn-primary btn-block" value="Submit" />

							</div>
						</div>
					</s:form>
					</center>
				</div>
			</div>
		</div>
								
								
	</body>
</html>
