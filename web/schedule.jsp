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
	<link rel="stylesheet" href="/resources/demos/style.css">		
	<script src="js/jquery-3.2.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function() {
                $('#confirmComplete').on('show.bs.modal', function(e) {
                  var id = $(e.relatedTarget).data('id');
                  alert(id);
                  document.getElementById('confirm').value = id;
                  
                });
            });    
        </script>
	<title>Doctor's Schedule</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1>Current Schedule</h1>

			
			

			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<th width="15%"></th>
						<th width="17%">Owner Name</th>
						<th width="17%">Pet Name</th>
						<th width="16%">Date</th>
						<th width="35%">Comments</th>
					</thead>
					<tbody>
						<tr>
							<td>
								<!--<input type="hidden" name="controlnum" value="${item.control_number}" >-->         
								<button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#confirmComplete">mark as complete</button>
							</td>
							<td>
								some dude
							</td>
							<td>
								bruts
							</td>
							<td>
								03/7/2017
							</td>
							<td>
								big black doge
							</td>
						</tr>	

						

						<s:iterator value="#session.appointments" var="record">	
						<tr>

                                                    <td><button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#confirmComplete" data-id="<s:property value="#record.appointmentNumber"/>"> Mark as complete</button></td>
							<td><s:property value="#record.appointmentNumber"/> <s:property value="%{#record.information.ownerName}" /></td>
							<td><s:property value="%{#record.information.patientName}" /></td>
							<td><s:property value="#record.date" /></td>
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
		
		</div>        
	</body>
</html>
