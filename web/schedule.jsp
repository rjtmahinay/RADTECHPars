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
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1 align="center"><font face="roboto">Current Schedule</font></h1>




			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<th width="10%">Completed</th>
						<th width="10%">Canceled</th>
						<th width="15%">Owner Name</th>
						<th width="15%">Pet Name</th>
						<th width="15%">Date</th>
						<th width="35%">Comments</th>
					</thead>
					<tbody>
						<s:iterator value="#session.appointments" var="record">	
						<tr>

							<td><button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#confirmComplete" data-id="<s:property value="%{#record.appointmentNumber}" />"><span class="glyphicon glyphicon-ok"></span></button></td>
							<td><button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmCancel" data-id="<s:property value="%{#record.appointmentNumber}" />"><span class="glyphicon glyphicon-remove"></span></button></td>
							<td><s:property value="%{#record.information.ownerName}" /></td>
                                                        <td><s:property value="%{#record.information.patientName}" /></td>
                                                        <td><s:date name="#record.date" format="MM/dd/yyyy"/></td>
							<td><s:property value="#record.comment" /></td>

						</tr>
												</s:iterator>

					</tbody>
				</table>
			</div>
			<div class="modal fade" id="confirmComplete">
							<s:form action="accomplishAppointment">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">Appointment Complete?</h3>
						</div>
						<div class="modal-body">
							<p align="center">This appointment will be marked as accomplished?</p>
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
