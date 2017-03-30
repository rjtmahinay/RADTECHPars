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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>User Profile</title>
		<style>
		
			
		body
		{
			overflow-x:hidden;
		}
		.table-fixed th {
		width:100%;
		float:left;
		height:50px;
		border-bottom-width: 0;
		}	
		.table-fixed thead {
		width: 97%;
		}
		.table-fixed tbody {
		  height: 150px;
		  overflow-y: auto;
		  overflow-x:hidden;
		  width: 101.4%;
		}
		.table-fixed tbody, .table-fixed tr, .table-fixed td {
		  display: block;
		}
		 .table-fixed tbody td, .table-fixed thead > tr >th{
		  width:50%;
		  height:50px;
		  float: left;
		  border-bottom-width: 0;
		}
		</style>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">
			<div class="row">
				<font face="roboto">
				<div class="col-md-6">
					
					<h1>User Profile</h1>
				</div>
				<div class="col-md-6">
					<h1>Next Appointment: <s:date name="%{#session.currentRecord.nextAppointment}" format="MM/dd/yyyy"/></h1>
				</div>
				</font>
			</div>
				<div class="row">
					<div class="col-md-6">
						
						<s:form action="updateRecord" theme="bootstrap" cssClass="form" method="POST">
						
						<div class="panel panel-default">
						<div class="table">
						<table class="table table-condensed table-">
							<tr>
								<td>
									Control Number:
								</td>
								<td>

									<s:property value="%{#session.currentRecord.controlNumber}"/>
								</td>
							</tr>

							<tr>
								<td>
									Owner Name:
								</td>
								<td>
									<s:textfield name="ownerName" value="%{#session.currentRecord.ownerName}" />
								</td>
							</tr>

							<tr>
								<td>
									Address:
								</td>
								<td>
									<s:textfield name="address" value="%{#session.currentRecord.address}" />
								</td>
							</tr>

							<tr>
								<td>
									Contact Number:
								</td>
								<td>
									<s:textfield name="contactNumber" value="%{#session.currentRecord.contactNumber}" />
								</td>
							</tr>

							<tr>
								<td>
									Pet Name:
								</td>
								<td>
									<s:textfield name="patientName" value="%{#session.currentRecord.patientName}" />
								</td>
							</tr>

							<tr>
								<td>
									Sex:
								</td>
								<td>
									
									<s:select cssClass="text text-block" name="sex" list="{'Male','Female'}" headerKey="%{#session.currentRecord.sex}" 
									headerValue="%{#session.currentRecord.sex}"/>
								</td>
							</tr>

							<tr>
								<td>
									Breed:
								</td>
								<td>
									<s:textfield name="breed" value="%{#session.currentRecord.breed}" />
								</td>
							</tr>
							<tr>
								<td>
									Color:
								</td>
								<td>
									<s:textfield name="color" value="%{#session.currentRecord.color}" />
								</td>
							</tr>

							<tr>
								<td>
									Date of birth:
								</td>
								<td>
									<script src="js/jquery-1.12.4.js"></script>
									<script src="js/jquery-ui.js"></script>
									<script>
									$( function() {
									 $( "#datepicker" ).datepicker();
									} );
									</script>
									<script>
									$( function() {
									 $( "#apppicker" ).datepicker();
									} );
									</script>
									
									
									
									<s:textfield name="dateinput" id="datepicker" value="%{#session.currentRecord.dateOfBirth}" placeholder="click here to set date"/></p>
								</td>
							</tr>


							<tr>
								<td>
									Weight:
								</td>
								<td>
									<s:textfield name="weight" value="%{#session.currentRecord.weight}" />
								</td>
							</tr>

						</table>
					</div>
					</div>
						<center>
						<s:submit value="Edit Record" cssClass="btn btn-primary"/>
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diagnoWindow">&plus; Diagnosis</button>
						</center>
						</s:form>
			`		
					
				</div>		
			
					
			
				<div class="col-md-1"></div>	
				<div class="col-md-3">
					<br><br>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 align="center">New Appointment</h3>
						</div>
						<div class="panel-body">
							<center>
							<s:form action="addAppointment" theme="bootstrap" cssClass="form" method="POST">
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

					</div>	
					</div><!-End of row in 2nd col->	
				</div>
			              <!-END OF ROW->


			<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover table-fixed">
					<thead>
						<th width="20%">Date</th>
						<th widht="80%">Diagnosis</th>
						

					</thead>
					<tbody>

                                                        <s:iterator value="%{#session.currentRecord.diagnosis}" var="diag">
                                                                <tr>
                                                                        <td><s:date name="%{#diag.dateDiagnosed}" format="MM/dd/yyyy"/></td>
                                                                        <td><s:property value="%{#diag.diagnosis}"/></td>
                                                                </tr>
                                                        </s:iterator>

						




					</tbody>
				</table>
			</div>


			<div class="modal fade" id="diagnoWindow">
				<div class="modal-dialog">
					<div class="modal-content">


						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 class="modal-title">Add Diagnosis</h3>
						</div>
						<s:form action="addDiagnosis" theme="bootstrap" cssClass="form" method="POST">
							<s:hidden name="id" value="%{#session.currentRecord.controlNumber}"/>
							<div class="modal-body">
								<div class="form-group">
									<%
                                                                            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                                                            Calendar cal = Calendar.getInstance();           
									%>
									
									<%--<s:textfield label="Date today:" name="date" value="<%=    dateFormat.format(cal.getTime()) + "\n" %>">--%>
									
									<p><b>Date today: <%=    dateFormat.format(cal.getTime()) + "\n" %></b></p>
								</div>
								
								<div class="form-group">
									<s:textarea label="Diagnosis" name="diagnosis" cols="70" rows="4" class="form-control" />
								</div>


								<div class="modal-footer form-group" >

									<s:submit cssClass="btn btn-primary btn-block" value="Add Diagnosis" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div> 
		</div>
				
	</body>
</html>
