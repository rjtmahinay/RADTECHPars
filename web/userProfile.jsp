<%-- 
	Document   : userProfile
	Created on : Feb 28, 2017, 4:03:36 PM
	Author     : Carl
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>User Profile</title>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">
			
			<s:form action="editRecord" theme="bootstrap" cssClass="form">
			
				<div class="row">
				<div class="col-md-6 col-sm-12">
					<h1>User Profile</h1>
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
									<s:textfield name="sex" value="%{#session.currentRecord.sex}" />
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
									Age:
								</td>
								<td>
									<s:textfield name="age" value="%{#session.currentRecord.age}" />
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

				<div class="col-md-6 col-sm-12">
					<br>
					<h2>Last Appointment</h2>


					<h1>        <!-INSERT APPOINTMENT HERE->        </h1>


					<br><br>
					<h3>Next Scheduled Appointment</h3>


					<h2>        <!-INSERT NEXT APPOINTMENT HERE->       </h2>


				</div>
			</div>              <!-END OF ROW->
			<center>
				<s:submit value="Edit Record" cssClass="btn btn-primary"/>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diagnoWindow">&plus; Diagnosis</button>
			</center>
			
			
			</s:form>
			
			<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th width="20%">Date</th>
						<th widht="40%">Diagnosis</th>
						<th width="40%">Comments</th>
						
					</thead>
					<tbody>
						<tr>
							<td>03/7/2017</td>
							<td>sample diag</td>
							<td>sample comment</td>
						</tr>
						<tr>
							<td>03/08/2017</td>
							<td>dog appears to be dead</td>
							<td>further treatment unnecessary</td>
						</tr>
						<%--
						<tr>
							<!--INSERT ITERATOR HERE-->
							<td></td>
						</tr>
						--%>
						
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

						<s:form action="addDiagnosis" theme="bootstrap" cssClass="form">
							<div class="modal-body">
								<div class="form-group">
									<s:textarea label="Diagnosis" name="diagnosis" cols="80" rows="4" class="form-control" />
								</div>

								<div class="form-group"> 
									<s:textarea label="Comments" name="comments" cols="80" rows="3" class="form-control"/>

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
