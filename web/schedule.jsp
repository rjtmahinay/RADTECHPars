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
	<link rel="stylesheet" href="css/jquery-ui.css">
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="icon" href="favicon.ico" type="image/x-icon">
	
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
				  document.getElementById('complete').value = id;

				});
			});   
                                $(document).ready(function() {
				$('#confirmCancel').on('show.bs.modal', function(e) {
				  var id = $(e.relatedTarget).data('id');
				  document.getElementById('cancel').value = id;

				});
			});
			
			
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
		
	<title>Doctor's Schedule</title>
	</head>
	<body>
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
                <h5> <s:actionerror/> </h5>
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
						<div class="col-md-4">Customer Name / Pet Name</div>
						<div class="col-md-3">Date / Breed</div>
						<div class="col-md-4">Transaction Type</div>
					</div>
				</div>
			</div>
			
			
			
			
			<s:iterator value="#session.appointments" var="record">
			<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse-next" data-parent="#accordion">
							<div class="row">
                                                            <s:if test="%{#session.currentUser.userType.equals('assistant')}">
								<div class="col-md-1">
										<button type="button" class="btn btn-danger btn-block btn-sm" data-toggle="modal" data-target="#confirmCancel" 
										data-id="<s:property value="%{#record.appointmentId}"/>">Cancel</button>	
								</div>	
                                                            </s:if>
                                                            
                                                            <s:if test="%{#session.currentUser.userType.equals('doctor')}">
                                                                <div class="col-md-1"><button type="button" class="btn btn-success btn-block btn-sm" data-toggle="modal" data-target="#confirmComplete" 
                                                                    data-id="<s:property value="%{#record.appointmentId}"/>">Complete</button></div>
                                                            </s:if>
								
							<s:if test="%{#session.currentUser.userType.equals('admin')}">
								<div class="col-md-1"></div>
							</s:if>	
								
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
                                                    <s:if test="%{#session.currentUser.userType.equals('doctor')}">
						<div class="row">
							<div class="col-md-1"> 
                                                            <s:url action="getVitals" var="vit">
                                                                             <s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
                                                            </s:url>
								<s:a href="%{vit}"><button class="btn btn-block btn-sm btn-primary" type="submit" name="action">Consult</button></s:a>
                                                                
							</div>
							<div class="col-md-4"><s:property value="%{#consultation.pet.name}"/></div>
							<div class="col-md-3"><s:property value="%{#consultation.pet.breed}"/></div>
						</div>
						<br>
                                                    </s:if>
                                                
                                                <s:if test="%{#session.currentUser.userType.equals('assistant')}">
						<div class="row">
							<div class="col-md-1"> 
                                                            <s:url action="getVitals" var="vit">
                                                                             <s:param name="consultationId"><s:property value="#consultation.consultationId"/></s:param>
                                                            </s:url>
                                                            <s:if test="%{#session.currentUser.userType.equals('assistant')}">
								<s:a href="%{vit}"><button class="btn btn-block btn-sm btn-primary" type="submit" name="action">Vitals</button></s:a>
                                                            </s:if>
                                                                
							</div>
							<div class="col-md-4"><s:property value="%{#consultation.pet.name}"/></div>
							<div class="col-md-3"><s:property value="%{#consultation.pet.breed}"/></div>
						</div>
						<br>
                                                    </s:if>
						</s:iterator>						
					</div>
				</div>
			</div>	
			</s:iterator>
			</div>
			
			
			
<%--END OF ASSISTANT VERSION, START OF DOCTOR VERSION, END OF ASSISTANT VERSION, START OF DOCTOR VERSION--%>
			

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
									<input type="hidden" id="cancel" name="input3"/>
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
			
			
			
			
			
			
			
			
			<div class="modal fade" id="confirmComplete">
				<s:form action="completeAppointment" method="post">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">Finish Appointment</h3>
						</div>
						<div class="modal-body">
							<p align="center">This will finish and remove the appointment from the schedule.</p>
						</div>
						<div class="modal-footer form-group" >
							<div class="row">
								<div class="col-md-6 col-sm-6">
									<input type="hidden" id="complete" name="input3"/>
									<center><s:submit type="button" cssClass="btn btn-primary btn-block" value="Continue" /></center>
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
