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
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>User Profile</title>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">

			<s:form action="updateRecord" theme="bootstrap" cssClass="form" method="POST">

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
									<label for="sex">Sex</label>
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
									<p>Date of Birth: 
									
									
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
				<center>
					<s:submit value="Edit Record" cssClass="btn btn-primary"/>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diagnoWindow">&plus; Diagnosis</button>
				</center>
			</s:form>
				</div>
				<div class="col-md-6 col-sm-12">
					<br>
					<h2>Next Appointment: <s:date name="%{#session.currentRecord.nextAppointment}" format="MM/dd/yyyy"/></h2>
					<s:form action="addAppointment" theme="bootstrap" cssClass="form" method="POST">
						<s:hidden name="id" value="%{#session.currentRecord.controlNumber}"/>
						<p>Date: <s:textfield name="dateinput" id="apppicker" value="" placeholder="click here to set date"/></p>
						<p><s:textarea name="comment" label="Description:" value=""/></p>
						<s:submit cssClass="btn btn-primary" value="Add Appointment" />											
					</s:form>	
				</div>
			</div>              <!-END OF ROW->


			<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th width="20%">Date</th>
						<th widht="80%">Diagnosis</th>
						

					</thead>
					<tbody>

                                                        <s:iterator value="%{#session.currentRecord.diagnosis}" var="diag">
                                                                <tr>
                                                                        <td><s:date name="%{#dateDiagnosed}" format="MM/dd/yyyy"/></td>
                                                                        <td><s:property value="%{#diag.diagnosis}"/></td>
                                                                </tr>
                                                        </s:iterator>

						<tr>
							<td>03/7/2017</td>
							<td>sample diag</td>
						</tr>
						<tr>
							<td>03/08/2017</td>
							<td>dog appears to be dead</td>
						</tr>




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
