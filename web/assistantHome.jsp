<%-- 
	Document   : assistantHome
	Created on : Apr 2, 2017, 8:56:29 AM
	Author     : Carl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>Animal Station and Veterinary Clinic Home</title>
	</head>
	<body background="dog.jpg">
		<s:include value="assistantNav.jsp"/>
		<div class="container-fluid">
			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						
						<th width="20%">Canceled</th>
						<th width="30%">Customer Name</th>
						
						<th width="20%">Date</th>
						<th width="30%">Transaction Type</th>
					</thead>
					<tbody>
						<tr>
							<td><button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmCancel" data-id="<s:property value="%{#record.appointmentNumber}" />"> <span class="glyphicon glyphicon-remove"></span></button></td>
							<td>Diaz</td>
							<td>today</td>
							<td>appointment</td>
						</tr>
						<s:iterator value="#session.appointments" var="record">	
						<tr>

							
							<td><button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmCancel" data-id="<s:property value="%{#record.appointmentNumber}" /><span class="glyphicon glyphicon-remove"></span></button></td>
							<td><s:property value="%{#record.information.ownerName}" /></td>	
							<td><s:date name="#record.date" format="MM/dd/yyyy"/></td>
							<td><s:property value="#record.transacType" /></td>

						</tr>
						</s:iterator>

					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
