<%-- 
	Document   : userProfile
	Created on : Feb 28, 2017, 4:03:36 PM
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
		
		
		<title>User Profile</title>
		<style>


		body
		{
			overflow-x:hidden;
		}
		.table-fixed th {
		
		}	
		.table-fixed thead {
		
		}
		.table-fixed tbody {
		
		}
		.table-fixed tbody, .table-fixed tr, .table-fixed td {
		
		}
		 .table-fixed tbody td{
		
		  
		
		}
		</style>
	</head>
	<body background="dog.jpg">
		<s:include value="home.jsp"/>

		<div class="container-fluid">
				<s:form action="updateRecord" theme="bootstrap" cssClass="form" method="POST">
				<div class="row">
					<div class="col-md-3">
						<font face="roboto">
							<h1>Customer Profile</h1>
						</font>
					</div>
					<div class="col-md-3">	
						<br>
						<p align="right"><s:submit value="Edit Record" cssClass="btn btn-primary"/> 
							<button type="button" data-id="" class="btn btn-danger"         
							data-toggle="modal" data-target="#confirmArchive"><span class="glyphicon glyphicon-trash"></span></button></p>
					</div>	
				</div>
					
					
					
				<div class="modal fade" id="confirmArchive">
					<s:form action="toArchive">
					<input type="hidden" name="idinput" value="" id="arcinput"/>
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 align="center" class="modal-title">Confirm Archive</h3>
							</div>
							<div class="modal-body">
								<p align="center">Reason for archive:</p>
								<center><s:textfield name="reason"/></center>
							</div>
							<div class="modal-footer form-group" >
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<center><s:submit type="button" cssClass="btn btn-danger btn-block" value="Archive" /></center>
									</div>
									<div class="col-md-6 col-sm-6">
										<center><button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">Cancel</button></center>
									</div>

								</div>
							</div>
						</div>


					</div>
					</s:form>
				</div>	
					
				<div class="row">
					<div class="col-md-6">

						


						<div class="table">
						<table class="table table-condensed table-">
							<tr>
								<td>Customer Number:</td>
								<td><s:property value="%{#session.currentRecord.controlNumber}"/></td>
							</tr>

							<tr>
								<td>Owner Name:</td>
								<td><s:textfield name="ownerName" value="%{#session.currentRecord.ownerName}" /></td>
							</tr>

							<tr>
								<td>Address:</td>
								<td><s:textfield name="address" value="%{#session.currentRecord.address}" /></td>
							</tr>

							<tr>
								<td>Contact Number:</td>
								<td><s:textfield name="contactNumber" value="%{#session.currentRecord.contactNumber}" /></td>
							</tr>

						</table>
						</div>

					</div>
				</div>		

					<center>
					
					<%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diagnoWindow">&plus; Diagnosis</button>--%>
					<br><br>
					</center>
					</s:form>

				<h1>Pet List  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPet"><span class="glyphicon glyphicon-plus"></span></button></h1>
				
				
				<div class="modal fade" id="addPet">
				<div class="modal-dialog">
					<div class="modal-content">
						<center>

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">Add Record</h3>
							</div>

							<!-INSERT FORM ACTION HERE->


							<s:form action="addPet" theme="bootstrap" cssClass="form" method="POST">
								<div class="modal-body">
									<div class="table">
									<table class="table table-condensed">
										<tr>
											<td><b>Pet Name:</b></td>
											<td><s:textfield name="patientName" placeholder=""/></td>
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
											<td><b>Sex:</b></td>
											<td><s:select cssClass="text text-block" name="sex" list="{'', 'Male','Female'}" headerKey="-1"/></td>
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
				
				
			<s:form action="addAppointment" theme="bootstrap" cssClass="form" method="POST">	
				<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th width="2%"></th>
						<th>Name</th>
						<th>Breed</th>
						<th>Color</th>
						<th>Sex</th>
						<th>Date of Birth</th>
						<th>Weight</th>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" value=""></td>
							<td>Uvu</td>
							<td>dog</td>
							<td>black</td>
							<td>male</td>
							<td>03/28/1997</td>
							<td>16</td>
						</tr>

						<%--
						<s:iterator value="%{#session.currentRecord.}" var="diag">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						</s:iterator>
						--%>
					</tbody>
				</table>
			</div>

			<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
			<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			<script>
			$( function() {
			  $( "#datepicker" ).datepicker();
			} );
			</script>
			<div class="row">
				<div class="col-md-6">
					
					<p align="right"><s:submit cssClass="btn btn-primary btn-sm" value="Create Appointment/s" /></p>
				</div>	
				<div class="col-md-6">
					
					<s:textfield name="dateinput" id="datepicker" placeholder="mm/dd/yyyy" />
				</div>
			</div>
					
			</s:form>		
					<%--
					<div class="panel panel-default">
						<div class="panel-body">
							<center>
							<s:form action="addPet" theme="bootstrap" cssClass="form" method="POST">
							<s:hidden name="id" value="%{#session.currentRecord.controlNumber}"/>
							<p><s:textfield name="dateinput" label="Date:" id="apppicker" value="" placeholder="click here to set date"/></p>
							<p><s:textarea name="comment" label="Description:" cols="22" value=""/></p>
							</center>
						</div>
						<div class="panel-footer">
							<center>
								<s:submit cssClass="btn btn-primary" value="+ Appointment" />
							</center>
						</div>											
							</s:form>
					</div><!-END OF ROW->

			

			--%>

			 
		</div>

	</body>
</html>
