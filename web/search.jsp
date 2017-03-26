<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<title>RADTECH PARS</title>
	</head>
	<body>
	   <s:include value="home.jsp" />
	   <div class="container-fluid">
			<s:form action="searchDatabase" namespace="/">
				<br>
				<div class="row">
					<div class="col-md-3 col-sm-0"></div>

					<div class="col-md-2 col-sm-4">
						<center>
						
                                                    <s:select name="searchType" list="{'Control Number', 'Address', 'Date of Birth', 'Breed', 'Color', 'Contact Number', 'Owner Name', 'Patient Name', 'Sex', 'Weight'}" 
                                                              headerKey="-1" headerValue="Select Search Type"/>
						</center>
					</div>

					<div class="col-md-2 col-sm-4">
						<center>
						   <s:textfield name="searchInput" />
						</center>
					</div>    
					<div class="col-md-2 col-sm-4">
						<center>
							<s:submit cssClass="btn btn-primary btn-sm btn-block" name="submit" value="submit"/>
						</center>
					</div>
					<div class="col-md-3 col-sm-0"></div>
				</div>
				<br>		
			</s:form>
		 
		<div class="table table-responsive">
			<table class="table table-bordered table-hover table-inverse table-striped">
				<thead>
					<tr>
						<th>Update</th>
						<th>Delete</th>
						<th>#</th>                                
						<th>Owner Name</th>
						<th>Address</th>
						<th>Contact Number</th>
						<th>Pet Name</th>
						<th>Sex</th>
						<th>Breed</th>
						<th>Date of birth</th>
						<th>Weight</th>
					</tr>
				</thead>

				<tbody>

					<s:iterator value="#session.search" var="record">
						<tr>
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
								<td><s:property value="#record.controlNumber" /></td>
								<td><s:property value="#record.ownerName" /></td>
								<td><s:property value="#record.address" /></td>
								<td><s:property value="#record.contactNumber" /></td>
								<td><s:property value="#record.patientName" /></td>
								<td><s:property value="#record.sex" /></td>
								<td><s:property value="#record.breed" /></td>
								<td><s:property value="#record.dateOfBirth" /></td>
								<td><s:property value="#record.weight" /></td>
							</tr>
						</s:iterator>
				</tbody>    
			</table>
		</div>    
	</div>


	</body>
</html>
