<%-- 
    Document   : petVitals
    Created on : Apr 5, 2017, 10:58:45 PM
    Author     : Carl
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.text.SimpleDateFormat, java.util.Calendar, java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		
		
		<title>Vitals and Diagnosis</title>
		

	</head>
	<body background="">
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<font face="roboto">
				<h2>Customer Profile</h2>
			</font>
			<div class="table">
			<table class="table table-bordered ">
				<thead>
					<th width="25%">Customer Number</th>
					<th width="25%">Customer Name</th>
					<th width="25%">Address</th>
					<th width="25%">Contact Number</th>
				</thead>
				<tbody>
				<tr>
					<td><s:property value="%{#session.currentRecord.controlNumber}"/></td>
					<td><s:property value="%{#session.currentRecord.ownerName}" /></td>
					<td><s:property  value="%{#session.currentRecord.address}" /></td>
					<td><s:property value="%{#session.currentRecord.contactNumber}" /></td>
				</tr>
				</tbody>
			</table>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<s:form action="petVitals" theme="bootstrap" cssClass="form" method="POST">
						<div class="panel-heading">
							<center>
								<s:select name="selectPet" list="{'pet1', 'pet2', 'pet3'}" headerKey="-1"/>
								<br>
							</center>
						</div>
						
						<div class="panel-body">
							
							<div class="table table-responsive">
								<table class="table table-bordered">
									<tr>
										<td>Temperature: </td>
										<td><center><s:textfield name="temperature" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Weight: </td>
										<td><center><s:textfield name="weight" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Eyes: </td>
										<td><center><input type="checkbox" name="eyes"></center></td>
									</tr>
									<tr>
										<td>Ears: </td>
										<td><center><input type="checkbox" name="ears"></center></td>
									</tr>
									<tr>
										<td>Nose: </td>
										<td><center><input type="checkbox" name="nose"></center></td>
									</tr>
									<tr>
										<td>Throat: </td>
										<td><center><input type="checkbox" name="throat"></center></td>
									</tr>
									<tr>
										<td>Derma: </td>
										<td><center><input type="checkbox" name="derma"></center></td>
									</tr>
									<tr>
										<td>Gums: </td>
										<td><center><input type="checkbox" name="gums"></center></td>
									</tr>
									<tr>
										<td>Lymph Nodes: </td>
										<td><center><input type="checkbox" name="lymphNodes"></center></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="panel-footer">
							<center><s:submit cssClass="btn btn-primary" name="submit" value="Save"/></center>
						</div>			
						</s:form>		
					</div>
				</div>
				
<!-END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, ->
				
				<div class="col-md-6">
					<div class="panel panel-default">
						<s:form action="addDiagnosis" theme="bootstrap" cssClass="form" method="POST">
						<div class="panel-body">
							<center>
								<s:select name="selectPet" list="{'pet1', 'pet2', 'pet3'}" headerKey="-1"/>
								
							</center>
						</div>
						
						<div class="panel-body">
							<h4>Diagnosis/Comments</h4>
							<center>
							<s:textarea name="diagnosis"  rows="5" cols="84" value=""/>
							</center>
							<br>
							<h4>Prescription</h4>
							<s:select name="selectPet" list="{'med1', 'med2', 'med3'}" headerKey="-1"/>
						</div>
						<div class="panel-footer">
							<center><s:submit cssClass="btn btn-primary" name="submit" value="Save"/></center>
						</div>			
						</s:form>		
					</div>
				</div>
				
			</div>
				
		</div>
	</body>
</html>
