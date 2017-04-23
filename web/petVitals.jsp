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
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
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
					<s:if test="%{#session.currentUser.userType.equals('assistant')}">
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
										<td>Temperature: </td>
										<td><center><s:textfield name="input1" placeholder=""/></center></td>
									</tr>
									<tr>
										<td>Weight: </td>
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
					</s:if>
					
<!-DOCTOR'S VERSION OF FIRST HALF, DOCTOR'S VERSION OF FIRST HALF, DOCTOR'S VERSION OF FIRST HALF, DOCTOR'S VERSION OF FIRST HALF, -->					
					

					<s:if test="%{#session.currentUser.userType.equals('doctor')}">
							<div class="panel panel-default">
								<div class="panel-heading">
									<center><s:property value="%{#session.currentConsultation.pet.name}" /></center>
								</div>
								<div class="panel-body">
									<div class="table table-responsive">
										<table class="table table-bordered table-condensed">
											<thead>
												
												<th width="40%">Part</th>
												<th width="60%"><center>Value / Comment</center></th>
												
											</thead>	

											<tr>
												<td>Temperature: </td>
												<td><center><s:property value="#session.currentConsultation.temperature"/></center></td>
											</tr>
											<tr>
												<td>Weight: </td>
												<td><center><s:property value="#session.currentConsultation.weight"/></center></td>
											</tr>
											<tr>
												<td>Eyes: </td>
												<td><center><s:property value="#session.currentConsultation.eyes"/></center></td>
											</tr>
											<tr>
												<td>Ears: </td>
												<td><center><s:property value="#session.currentConsultation.ears"/></center></td>
											</tr>
											<tr>
												<td>Nose: </td>
												<td><center><s:property value="#session.currentConsultation.nose"/></center></td>
											</tr>
											<tr>
												<td>Throat: </td>
												<td><center><s:property value="#session.currentConsultation.throat"/></center></td>
											</tr>
											<tr>
												<td>Derma: </td>
												<td><center><s:property value="#session.currentConsultation.derma"/></center></td>
											</tr>
											<tr>
												<td>Gums: </td>
												<td><center><s:property value="#session.currentConsultation.gums"/></center></td>
											</tr>
											<tr>
												<td>Lymph Nodes: </td>
												<td><center><s:property value="#session.currentConsultation.lymphNodes"/></center></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
					</s:if>
					
					
					
					
				</div>
				
<!-END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, END OF FIRST HALF, ->
				
				<div class="col-md-6">
					<s:if test="%{#session.currentUser.userType.equals('doctor')}">
					<div class="panel panel-default">
						<s:form action="addDiagnosis" theme="bootstrap" cssClass="form" method="POST">
                                                    <s:hidden value="%{#session.currentConsultation.consultationId}" name="input3"/>
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
					</s:if>
				</div>
				
			</div>
				
		</div>
	</body>
</html>
