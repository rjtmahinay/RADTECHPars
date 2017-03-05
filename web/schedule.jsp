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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
                <title>Doctor's Schedule</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1>Current Schedule</h1>
			
			<center>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newAppointment">Add Appointment</button>
			</center>
			<div class="modal fade" id="newAppointment">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">New Appointment</h3>
						</div>
						<div class="modal-body">
							<s:form action="addRecord" theme="bootstrap" cssClass="form">
							<div class="modal-body">
								<center>

								<div class="form-group">
									<label for="controlNumber">Control Number</label>
									<s:textfield name="controlNumber" placeholder=""/>
								</div>
								
								<div class="form-group">
									<%--<s:datetimepicker name="date1" label="Format (MM-dd-yyyy)" displayFormat="dd-MMM-yyyy"  />--%>
								</div>

								<div class="modal-footer form-group" >
									<center>
									<s:submit cssClass="btn btn-primary btn-block" value="Add Appointment" />
									<s:reset value="clear" cssClass="btn btn-secondary btn-block"/>
									</center>
								</div>
								</center>
							</s:form>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			
			
			
			
			
			<div class="modal fade" id="confirmDelete">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">Confirm Delete</h3>
						</div>
						<div class="modal-body">
							<p align="center">Are you sure you want to delete this appointment?</p>
						</div>
						<div class="modal-footer form-group" >
							<div class="row">
								<div class="col-md-6 col-sm-6">
									<center><s:submit cssClass="btn btn-danger" value="Delete" /></center>
								</div>
								<div class="col-md-6 col-sm-6">
									<center><button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button></center>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<th>Delete</th>
						<th>#</th>
						<th>Date</th>
						<th>Comments</th>
					</thead>
					<tbody>
						<tr>
							<td>
								<!--<input type="hidden" name="controlnum" value="${item.control_number}" >-->         
								<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete">Delete</button>
							</td>
							<td>
								1
							</td>
							<td>
								sample
							</td>
							<td>
								sample
							</td>
						</tr>	
							
						<%--
							
						<s:iterator value="#session.view" var="record">	
						
						<tr>
							<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete">Delete</button></td>
							<td><s:property value="#record.controlNumber" /></td>
							<td><s:property value="#record.date" /></td>
							<td><s:property value="#record.comments" /></td>
							<td></td>
						</tr>
								
						--%>
					</tbody>
				</table>
			</div>
		</div>        
	</body>
</html>
