<%-- 
	Document   : editPet
	Created on : May 3, 2017, 4:07:52 PM
	Author     : Carl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="js/jquery-3.2.0.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>Edit Pet Information Here</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
		<h2>Edit Pet Information</h2>
		<s:form action="EditPet" theme="bootstrap" cssClass="form" method="POST">
			<div class="row">
				<div class="col-md-6">
			<div class="table table-responsive">
				<table class="table table-hover">
					<thead>
						<th width="50%"></th>
						<th width="50%"></th>
					</thead>
					<tbody>
					<tr>
						<td>Pet Name</td>
						<td><s:textfield name="name" placeholder=""/><s:actionerror/></td>
					</tr>
					
					<tr>
						<td>Breed</td>
						<td><s:textfield name="breed" placeholder=""/><s:actionerror/></td>
					</tr>
					
					<tr>
						<td>Color</td>
						<td><s:textfield name="color" placeholder=""/><s:actionerror/></td>
					</tr>
					
					<tr>
						<td>Gender</td>
						<td><s:select cssClass="text text-block" name="sex" list="{'Male','Female'}" headerKey="-1"/><s:actionerror/></td>
					</tr>
					
					<tr>
						<td>Date of Birth</td>
						<td><s:textfield name="petDateOfBirth"/><s:actionerror/></td>
					</tr>
					<tr>
						<td colspan="2">
							<center><s:submit cssClass="btn btn-primary" value="submit" /></center>
						</td>
					</tr>
					</tbody>
				</table>
				</div>		
				</div>				
			</div>
		</s:form>
		</div>
	</body>
</html>
