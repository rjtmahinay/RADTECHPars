<%-- 
	Document   : schedule
	Created on : Mar 5, 2017, 3:43:34 PM
	Author     : Carl
--%>
<%--<%@taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="icon" href="favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="/resources/demos/style.css">		
	<script src="js/jquery-3.2.0.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#confirmComplete').on('show.bs.modal', function(e) {
				  var id = $(e.relatedTarget).data('id');
				  document.getElementById('confirm').value = id;

				});
			});   
                        $(document).ready(function() {
				$('#confirmCancel').on('show.bs.modal', function(e) {
				  var id = $(e.relatedTarget).data('id');
				  document.getElementById('cancel').value = id;

				});
			});
		</script>
	<title>Doctor's Schedule</title>
	</head>
	<body background="dog.jpg">
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1 align="center"><font face="roboto">Current Schedule</font></h1>




			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<s:if test="%{#session.currentUser.userType=='doctor'}">
						<th width="10%">Diagnosis</th>	
						</s:if>
						<s:if test="%{#session.currentUser.userType=='assistant'}">
						<th width="10%"><center><span class="glyphicon glyphicon-edit"></span></center></th>
						<th width="10%"><center><span class="glyphicon glyphicon-remove"></span></center></th>
						</s:if>
						<th width="">Customer Name</th>
						<th>Pet Name</th>
						<th width="">Date</th>
						<th width="">Transaction Type</th>
					</thead>
					<tbody>
						<s:iterator value="#session.appointments" var="record">	
							
						<tr>
							
							<s:if test="%{#session.currentUser.userType=='doctor'}">
							<td><button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#confirmComplete" data-id="
								<s:property value="%{#record.appointmentNumber}" />">Consult</button></td>
							</s:if>
							<s:if test="%{#session.currentUser.userType=='assistant'}">
							<s:url action="getVitals" var="vit">
								<s:param name="id"><s:property value="#record.controlNumber"/></s:param>
							</s:url>
							<td><s:a href="%{vit}"><button class="btn btn-block btn-primary" type="submit" name="action">Vitals</button></s:a></td>
							<td><button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmCancel" data-id="
								<s:property value="%{#record.appointmentNumber}" />">Cancel</button></td>
							</s:if>
							<td><s:property value="%{#record.information.ownerName}" /></td>	
							<td><s:property value="%{#record.information.patientName}" /></td>
							<td><s:date name="#record.date" format="MM/dd/yyyy"/></td>
							<td><s:property value="#record.transacType" /></td>
							
						</tr>
												</s:iterator>

					</tbody>
				</table>
			</div>
			<div class="modal fade" id="initialDiagnosis">
							<s:form action="initialDiagnosis">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">Checklist</h3>
						</div>
						<div class="modal-body">
							<div class="table table-bordered">
								<table class="table table-responsive">
								
									<p align="center">Head <input type="checkbox" value="Head"></p>
								</table>
							</div>
						</div>
						<div class="modal-footer form-group" >
							<div class="row">
								<div class="col-md-6 col-sm-6">
									<input type="hidden" id="confirm" name="appinput"/>
									<center><s:submit type="button" cssClass="btn btn-primary btn-block" value="Continue" /></center>
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
								
			<div class="modal fade" id="confirmCancel">
							<s:form action="cancelAppointment">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">Cancel Appointment</h3>
						</div>
						<div class="modal-body">
							<p align="center">Delete appointment from schedule?</p>
						</div>
						<div class="modal-footer form-group" >
							<div class="row">
								<div class="col-md-6 col-sm-6">
									<input type="hidden" id="cancel" name="appinput"/>
									<center><s:submit type="button" cssClass="btn btn-danger btn-block" value="Delete" /></center>
								</div>
								<div class="col-md-6 col-sm-6">
										<center><button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">Back</button></center>
								</div>

							</div>
						</div>
					</div>
				</div>
							</s:form>
			</div>

		</div>        
	</body>
</html>
