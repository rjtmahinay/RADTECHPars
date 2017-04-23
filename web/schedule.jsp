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
			<%--
			$('.panel-collapse').collapse({toggle: false});
			$('body').on('click', '[data-toggle=collapse-next]', function (e) {
				e.preventDefault();

				// Try to close all of the collapse areas first
				var parent_id = $(this).data('parent');
				$(parent_id+' .panel-collapse').collapse('hide');

				// ...then open just the one we want
				var $target = $(this).parents('.panel').find('.panel-collapse');
				$target.collapse('toggle');
			});
			--%>
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
	<body background="">
		<s:include value="home.jsp"/>
		<script>
		$('.panel-collapse').collapse({toggle: false});
			$('body').on('click', '[data-toggle=collapse-next]', function (e) {
				e.preventDefault();

				// Try to close all of the collapse areas first
				var parent_id = $(this).data('parent');
				$(parent_id+' .panel-collapse').collapse('hide');

				// ...then open just the one we want
				var $target = $(this).parents('.panel').find('.panel-collapse');
				$target.collapse('toggle');
			});
		</script>	
		<div class="container-fluid">
			<h1 align="center"><font face="roboto">Current Schedule</font></h1>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						
						<div class="col-md-1">
							<s:if test="%{#session.currentUser.userType.equals('assistant')}">	
								Vitals
							</s:if>
							<s:if test="%{#session.currentUser.userType.equals('doctor')}">	
								Diagnosis
							</s:if>	
						</div>	
						<div class="col-md-4">Customer Name</div>
						<div class="col-md-3">Date</div>
						<div class="col-md-4">Transaction Type</div>
					</div>
				</div>
			</div>
			
			
			<s:if test="%{#session.currentUser.userType.equals('assistant')}">
			
			<s:iterator value="#session.appointments" var="record">
			<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse-next" data-parent="#accordion">
							<div class="row">
								<div class="col-md-1">
										<button type="button" class="btn btn-danger btn-block btn-sm" data-toggle="modal" data-target="#confirmCancel" 
										data-id=" <s:property value="%{#record.appointmentNumber}" />">Cancel</button>	
								</div>	
								<div class="col-md-4"><s:property value="%{#record.customer.name}"/></div>
								<div class="col-md-3">	<s:date name="#record.appointmentDate" format="MM/dd/yyyy"/></div>
								<div class="col-md-4"><s:property value="#record.transactionType"/></div>
							</div>
						</a>
					</h4>
				</div>
				<div class="panel-collapse collapse">
					<div class="panel-body">
						<s:iterator value="#record.consultations" var="consultation">
						<div class="row">
							<div class="col-md-1">
								<s:url action="getVitals" var="vit">
                                                                                 <s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
								</s:url>
								<s:a href="%{vit}"><button class="btn btn-block btn-sm btn-primary" type="submit" name="action">Vitals</button></s:a>
							</div>
							<div class="col-md-4">name<s:property value="%{consultation.pet.name}"/></div>
							<div class="col-md-3">breed<s:property value="%{consultation.pet.breed}"/></div>
						</div>
						<br>
						</s:iterator>						
					</div>
				</div>
			</div>
								
			</s:iterator>
			</div>
			</s:if>
			
			
			
<%--END OF ASSISTANT VERSION, START OF DOCTOR VERSION, END OF ASSISTANT VERSION, START OF DOCTOR VERSION--%>
			
			
			
			
			<s:if test="%{#session.currentUser.userType.equals('doctor')}">
				<s:iterator value="#session.appointments" var="record">
				<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle" data-toggle="collapse-next" data-parent="#accordion">
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-4"><s:property value="%{#record.customer.name}"/></div>
									<div class="col-md-3"><s:date name="#record.appointmentDate" format="MM/dd/yyyy"/></div>
									<div class="col-md-4"><s:property value="#record.transactionType"/></div>
								</div>
							</a>
						</h4>
					</div>
					<div class="panel-collapse collapse">
						<div class="panel-body">
							<s:iterator value="#record.consultations" var="consultation">
							<div class="row">
								<div class="col-md-1">
									<s:url action="doctorDiagnosis" var="doc">
										<s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
									</s:url>
									<s:a href="%{doc}"> <button class="btn btn-block btn-primary btn-sm" 
										type="submit" name="action">Vitals</button></s:a>
								</div>
								<div class="col-md-4">pet name<s:property value="%{consultation.pet.name}"/></div>
								<div class="col-md-3">breed<s:property value="%{consultation.pet.breed}"/></div>
							</div>
							<br>
							</s:iterator>						
						</div>
					</div>
				</div>

				</s:iterator>
				</div>
			</s:if>	
			<%--
			
			--%>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		<%--	
		<div class="container-fluid">	
			<h1 align="center"><font face="roboto">Current Schedule</font></h1>
			<div class="table-responsive">
				<table class="table table-bordered table-hover table-inverse" align="center">
					<thead>
						<s:if test="%{#session.currentUser.userType.equals('doctor')}">
						<th width="10%">Diagnosis</th>	
						</s:if>
						<s:if test="%{#session.currentUser.userType.equals('assistant')}">
						<th width="10%"><center><span class="glyphicon glyphicon-edit"></span></center></th>
						<th width="10%"><center><span class="glyphicon glyphicon-remove"></span></center></th>
						</s:if>
						<th width="">Customer Name</th>
						<th width="">Date</th>
						<th width="">Transaction Type</th>
					</thead>
					<tbody>
						<s:iterator value="#session.appointments" var="record">	
                                                    <tr>
                                                        </td>
                                                        <td><s:property value="%{#record.customer.name}"/></td>
                                                        <td><s:date name="#record.appointmentDate" format="MM/dd/yyyy"/></td>
                                                        <td><s:property value="#record.transactionType"/> </td>
                                                    </tr>
							<s:iterator value="#record.consultations" var="consultation">
                                                        <tr>
                                                            <td>
                                                                    <s:if test="%{#session.currentUser.userType.equals('assistant')}">
                                                                            <s:url action="getVitals" var="vit">
                                                                                    <s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
                                                                            </s:url>
                                                                            <s:a href="%{vit}"><button class="btn btn-block btn-primary" 
                                                                                    type="submit" name="action">Vitals</button></s:a>
                                                                    </s:if>
                                                                    <s:if test="%{#session.currentUser.userType.equals('doctor')}">
                                                                            <s:url action="doctorDiagnosis" var="doc">
                                                                                    <s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
                                                                            </s:url>
                                                                        <s:a href="%{doc}"> <button class="btn btn-block btn-primary" 
                                                                                type="submit" name="action">Vitals</button></s:a>
                                                                    </s:if>
                                                            </td>
                                                            <td><s:property value="%{consultation.pet.name}"/></td>
                                                            <td/>
                                                        </tr>
							</s:iterator>
						</s:iterator>
					</tbody>
				</table>
			</div>
		--%>				
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
