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
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.12.4.js"></script>
		<script src="js/jquery-ui.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
		
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script>
			
			
			
//			
//			$ (document).ready(function(e){
//				$ (document).on('click', '.btn-add', function(e)
//				{
//					e.preventDefault();
//
//					var controlForm = $('.controls form:first'),
//						currentEntry = $(this).parents('.entry:first'),
//						newEntry = $(currentEntry.clone()).appendTo(controlForm);
//
//					newEntry.find('input').val('');
//					controlForm.find('.entry:not(:last) .btn-add')
//						.removeClass('btn-add').addClass('btn-remove')
//						.removeClass('btn-success').addClass('btn-danger')
//						.html('<span class="glyphicon glyphicon-minus"></span>');
//				}).on('click', '.btn-remove', function(e)
//				{
//					$(this).parents('.entry:first').remove();
//
//					e.preventDefault();
//					return false;
//				});
//			});
//			

			</script>
			
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
		

		<title>Vitals and Diagnosis</title>


	</head>
	<body background="">
		
		
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<font face="roboto">
			<div class="row">
				<div class="col-md-6">
					<h2>Customer Profile</h2>
				</div>
				<div class="col-md-5"></div>
				<div class="col-md-1">
					<br>
					
					<p align="right"><button class="btn btn-primary btn-sm btn-block" 
								onClick="window.open('historize.action?input1=<s:property value="#session.currentConsultation.pet.petId"/>'); 
								return false;"><span class="icon">History</span></button></p>
					
				</div>
			</div>	
			</font>
			<div class="table">
			<table class="table table-bordered ">
				<thead>
					<th width="25%">Customer Name</th>
					<th width="25%">Pet Name</th>
					<th width="25%">Address</th>
					<th width="25%">Contact Number</th>
				</thead>
				<tbody>
				<tr>
					<td><s:property value="%{#session.currentConsultation.appointment.customer.name}" /></td>
					<td><s:property value="%{#session.currentConsultation.pet.name}"/></td>
					<td><s:property value="%{#session.currentConsultation.appointment.customer.address}" /></td>
					<td><s:property value="%{#session.currentConsultation.appointment.customer.contactNumber}" /></td>
					<s:actionerror/>
				</tr>
				</tbody>
			</table>
			</div>
			<div class="row">
				<div class="col-md-6">
					
					<div class="panel panel-default">
						<s:form action="setVitals" theme="bootstrap" cssClass="form" method="POST">
						<div class="panel-heading">
							<center>
								<s:hidden name="input3" value="%{#session.currentConsultation.consultationId}"/>
								<br>
							</center>
						</div>

						<div class="panel-body">

							<div class="table table-responsive">
								<table class="table table-bordered table-condensed">
									<tr>
										<td>Temperature(°C): </td>
										<td><center><s:textfield name="input1" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Weight(kg): </td>
										<td><center><s:textfield name="input2" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Eyes: </td>

										<td><center><s:textfield name="eyes" placeholder=""/></center></td>

									</tr>
									<tr>

										<td>Ears: </td>
										<td><center><s:textfield name="ears" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Nose: </td>
										<td><center><s:textfield name="nose" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Throat: </td>
										<td><center><s:textfield name="throat" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Derma: </td>
										<td><center><s:textfield name="derma" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Gums: </td>
										<td><center><s:textfield name="gums" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Lymph Nodes: </td>
										<td><center><s:textfield name="lymphNodes" placeholder=""/></center></td>
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



				<div class="col-md-6">
					<s:form action="addDiagnosis" theme="bootstrap" cssClass="form" method="POST">
						<s:hidden value="%{#session.currentConsultation.consultationId}" name="input3"/>
					<div class="panel panel-default">
						<div class="panel-body">

							<h4>Prescription</h4>
								<center>	
									<s:textarea name="medicineName" rows="5" cols="84" value=""/>
								</center>

							<h4>Diagnosis/Comments</h4>
								<center>
									<s:textarea name="diagnosis"  rows="5" cols="84" value=""/>
								</center>
							<br>


							<br>
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
