<%-- 
	Document   : welcome
	Created on : Feb 28, 2017, 3:20:14 PM
	Author     : Carl
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		
                <script>
                    $('form').each(function() { this.reset() });    
                </script>
		<title>Welcome To Radtech PARS</title>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">
			
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRecord">Add Record</button>
			
			<div class="modal fade" id="addRecord">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<center>

						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 class="modal-title">Add Record</h3>
						</div>

							<!-INSERT FORM ACTION HERE->


						<s:form action="addRecord" theme="bootstrap" cssClass="form" method="POST">
							<div class="modal-body">

								
									<label for="ownerName">Owner Name</label>
									<s:textfield name="ownerName" placeholder=""/>
								

								
									<label for="address">Address</label>
									<s:textfield name="address" placeholder=""/>
								

								
									<label for="contactNumber">Contact Number</label>
									<s:textfield name="contactNumber" placeholder=""/>
								

								
									<label for="patientName">Pet Name</label>
									<s:textfield name="patientName" placeholder=""/>
								
																
									<label for="color">Color</label>
									<s:textfield name="color" placeholder=""/>
								

								
									<label for="sex">Sex</label>
									<s:select cssClass="text text-block" name="sex" list="{'', 'Male','Female'}" headerKey="-1"/>
								

								
									<label for="breed">Breed</label>
									<s:textfield name="breed" placeholder=""/>
								

								
									<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
									<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
									<script>
									$( function() {
									  $( "#datepicker" ).datepicker({
										  minDate: -7300,
										changeMonth: true,
										changeYear: true
									  });
									} );
									</script>

									<label for="date">Date of birth</label>
									<s:textfield name="dateinput" id="datepicker" placeholder="click here to set date"/>
								

								
									<label for="weight">Weight</label>
									<s:textfield name="weight" placeholder=""/>
								





								<div class="modal-footer form-group" >
									<center>
									<s:submit cssClass="btn btn-primary btn-block" value="submit" />
									<s:reset value="clear" cssClass="btn btn-secondary btn-block"/>
									</center>
								</div>
							</div>
						</s:form>
						  </center>  
					</div>
				</div>
			</div>
			

			<div class="table-responsive">
				<table style="page-break-before: always; page-break-after: always;" class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<tr>
							<th>Update</th>
							<th>Archive</th>    
							<th>#</th>                                
							<th>Owner Name</th>
							<th>Address</th>
							<th>Contact Number</th>
							<th>Pet Name</th>
							<th>Sex</th>
							<th>Breed</th>
							<th>Color</th>
							<th>Date of Birth</th>
							<th>Weight</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#session.view" var="record">
							<tr style="page-break-inside: avoid;">
								<s:url action="getRecord" var="rec">
									<s:param name="id"><s:property value="#record.controlNumber"/></s:param>
								</s:url>
								<s:url action="toArchive" var="arc">
									<s:param name="id"><s:property value="#record.controlNumber"/></s:param>
								</s:url>
								<td>       
									<s:a href="%{rec}"><button class="btn btn-block btn-primary" type="submit" name="action">Update</button></s:a>
								</td>

								<td>
									<button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmArchive">Archive</button>
									
								<div class="modal fade" id="confirmArchive">
									<div class="modal-dialog modal-sm">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h3 align="center" class="modal-title">Confirm Archive</h3>
											</div>
											<div class="modal-body">
												<p align="center">Are you sure you want to archive this record?</p>
											</div>
											<div class="modal-footer form-group" >
												<div class="row">
													<div class="col-md-6 col-sm-6">
														<center><s:a href="%{arc}"><button type="button" class="btn btn-danger btn-block" data-toggle="modal">Archive</button></s:a></center>
													</div>
													<div class="col-md-6 col-sm-6">
														<center><button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">Cancel</button></center>
													</div>

												</div>
											</div>
										</div>


									</div>
								</div>
									
								</td>
								<td><s:property value="#record.controlNumber"/> </td>
								<td><s:property value="#record.ownerName" /></td>
								<td><s:property value="#record.address" /></td>
								<td><s:property value="#record.contactNumber" /></td>
								<td><s:property value="#record.patientName" /></td>
								<td><s:property value="#record.sex" /></td>
								<td><s:property value="#record.breed" /></td>
								<td><s:property value="#record.color" /></td>
								<td><s:date name="#record.dateOfBirth" format="MM/dd/yyyy"/></td>
								<td><s:property value="#record.weight" /></td>
							</tr>
						</s:iterator>

						<!-INSERT TABLE BODY HERE->


					</tbody>
				</table>
			</div>

		</div>
	</body>
</html>
