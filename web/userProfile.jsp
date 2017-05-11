<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.text.SimpleDateFormat, java.util.Calendar, java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
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
		<script src="js/jquery-1.12.4.js"></script>
		<script src="js/jquery-ui.js"></script>
			<script>
			$( function() {
				var availableTags = [
				  "Labrador Retriever","German Shepherd","Bulldog","Terrier","Golden Retriever",
				  "Chihuahua","Dachshund","Beagle","Boxer","Shih Tzu","Spitz","Pug","Rottwieler","Maltese",
				  "Doberman",

				  "Siamese","Persian","Maine Coon","Ragamuffin","Ragdoll","American Shorthair","Tonkinese",
				  "Russian Blue","Bengal","Scottish Fold",



				];
				$( "#breeds" ).autocomplete({
				  source: availableTags
				});  

			  } );
		</script>
		<style>
			.ui-autocomplete {z-index: 1061 !important;}
		</style>
		
		<title>User Profile</title>
		<style>
		

		
		</style>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">
				<s:form action="editCustomer" theme="bootstrap" cssClass="form" method="POST">
				<div class="row">
					<div class="col-md-3">
						<font face="roboto">
							<h1>Customer Profile</h1>
						</font>
					</div>
					<div class="col-md-3">	
						<br>
						<p align="right">
							<s:if test="%{#session.currentUser.userType.equals('assistant')}">	
								<s:submit value="Edit Record" cssClass="btn btn-primary"/> 
							</s:if>
							<s:if test="%{#session.currentUser.userType.equals('doctor')}">	
								<button type="button" data-id="" class="btn btn-danger"         
								data-toggle="modal" data-target="#confirmArchive"><span class="glyphicon glyphicon-trash"></span></button>
							</s:if>
						</p>	
					</div>	
				</div>
					
					
					
					
					
				<div class="row">
					<div class="col-md-6">

						


						<div class="table">
						<table class="table table-condensed table-">
							<s:if test="%{#session.currentUser.userType.equals('assistant')}">
								<tr>
									<td>Customer Number:</td>
									<s:hidden name="input3" value="%{#session.currentCustomer.customerId}"/>
									<td><s:property value="%{#session.currentCustomer.customerId}"/></td>
								</tr>

								<tr>
									<td>Owner Name:</td>
									<td><s:textfield name="name" value="%{#session.currentCustomer.name}" /></td>
								</tr>

								<tr>
									<td>Address:</td>
									<td><s:textfield name="address" value="%{#session.currentCustomer.address}" /></td>
								</tr>

								<tr>
									<td>Contact Number:</td>
									<td><s:textfield name="contactNumber" value="%{#session.currentCustomer.contactNumber}" /></td>
								</tr>
							</s:if>
							<s:if test="%{#session.currentUser.userType.equals('doctor')}">
								<tr>
									<td>Customer Number:</td>
									<td><s:property value="%{#session.currentCustomer.customerId}"/></td>
								</tr>

								<tr>
									<td>Owner Name:</td>
									<td><s:property value="%{#session.currentCustomer.name}" /></td>
								</tr>

								<tr>
									<td>Address:</td>
									<td><s:property value="%{#session.currentCustomer.address}" /></td>
								</tr>

								<tr>
									<td>Contact Number:</td>
									<td><s:property value="%{#session.currentCustomer.contactNumber}" /></td>
								</tr>
							</s:if>
						</table>
						</div>

					</div>
				</div>	
					</s:form>

			<div class="modal fade" id="confirmArchive">
					<s:form action="toArchive">
						<input type="hidden" name="input3" value="<s:property value='#session.currentCustomer.customerId'/>"/>
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 align="center" class="modal-title">Confirm Archive</h3>
							</div>
							<div class="modal-body">
								<p align="center">Reason for archive:</p>
								<center><s:textfield name="reasonInput"/></center>
							</div>
							<div class="modal-footer form-group" >
								<div class="row">
									<div class="col-md-6 col-sm-6">
										<center><s:submit type="button" cssClass="btn btn-danger btn-block" value="Archive" /></center>
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
				<h1>
					Pet List 
					<s:if test="%{#session.currentUser.userType.equals('assistant')}"> 
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPet"><span class="glyphicon glyphicon-plus"></span></button> 
					</s:if>
				</h1>
				
				
				<div class="modal fade" id="addPet">
				<div class="modal-dialog">
					<div class="modal-content">
						<center>

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">Add Pet</h3>
							</div>

							<!-INSERT FORM ACTION HERE->


							<s:form action="addPet" theme="bootstrap" cssClass="form" method="POST">
								
								<div class="modal-body">
									<div class="table">
									<table class="table table-condensed">
										<tr>
											<td><b>Pet Name:</b></td>
											<td><s:textfield name="name" placeholder=""/></td>
										</tr>
										
										<tr>
											<td><b>Breed:</b></td>
											<td><s:textfield name="breed" id="breeds" placeholder=""/></td>
										</tr>
										<tr>
											<td><b>Color:</b></td>
											<td><s:textfield name="color" placeholder=""/></td>
										</tr>
										<tr>
											<td><b>Sex:</b></td>
											<td><s:select cssClass="text text-block" name="sex" list="{'Male','Female'}" headerKey="-1"/></td>
										</tr>
										<tr>
										<script src="js/jquery-1.12.4.js"></script>
										<script src="js/jquery-ui.js"></script>
										<script>
										$( function() {
										  $( "#date" ).datepicker({maxDate: "+0M +0D", changeMonth: true, changeYear: true});
										} );
										</script>	
											<td><b>Date of birth:</b></td>
											<td>
											<s:textfield name="dateInput" id="date" />
											</td>
										</tr>

									</table>
								</div>

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
				
		<s:if test="%{#session.currentUser.userType.equals('assistant')}">
				
			<s:form action="addAppointment" theme="bootstrap" cssClass="form" method="POST">	
				<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th width="2%"></th>
						<%--<th width="2%">Edit</th>--%>
						<%--<th width="8%"></th>--%>
						<th>Name</th>
						<th>Breed</th>
						<th>Color</th>
						<th>Sex</th>
						<th>Date of Birth</th>
					</thead>
					<tbody>
						<s:iterator value="%{#session.currentCustomer.pets}" var="pet">
						<tr>
							<td><s:checkbox fieldValue="%{#pet.petId}" name="input3"/></td>
							<%--<td><button class="btn btn-block btn-primary btn-sm" type="submit" name="action">History</button></td>--%>
							<s:url action="fetchPet" var="fet"/>
							<%--<td><s:a href="%{fet}"><button class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></button></s:a></td>--%>
							<td><s:property value="#pet.name"/></td>
							<td><s:property value="#pet.breed"/></td>
							<td><s:property value="#pet.color"/></td>
							<td><s:property value="#pet.sex"/></td>
							<td><s:date name="#pet.dateOfBirth" format="MM/dd/yyyy"/></td>
						</tr>
						</s:iterator>
						
					</tbody>
				</table>
			</div>

			<script src="js/jquery-1.12.4.js"></script>
			<script src="js/jquery-ui.js"></script>
			<script>
			$( function() {
			  $( "#appointmentDate" ).datepicker({minDate: -0, maxDate: "+3M"});
			} );
			</script>
			<div class="row">
				<div class="col-md-6">
					
					<p align="right"><s:submit cssClass="btn btn-primary btn-sm" value="Create Appointment/s" /></p>
				</div>	
				<div class="col-md-6">
					
					<s:textfield name="dateInput" id="appointmentDate" placeholder="mm/dd/yyyy" />
				</div>
			</div>
					
			</s:form>	
		</s:if>	
			
			
<%--DOCTOR'S VERSION OF USERPROFILE , DOCTOR'S VERSION OF USERPROFILE, DOCTOR'S VERSION OF USERPROFILE--%>		
			
		
		<s:if test="%{#session.currentUser.userType.equals('doctor')}">
			<div class="table table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th width="8%"></th>
						<th>Name</th>
						<th>Breed</th>
						<th>Color</th>
						<th>Sex</th>
						<th>Date of Birth</th>
						<!--<th width="3%"></th>-->
					</thead>
					<tbody>
						<s:iterator value="%{#session.currentCustomer.pets}" var="pet">
						<tr>
							<td><button class="btn btn-primary btn-block btn-sm" onClick="window.open('historize.action?input1=<s:property value="#pet.petId"/>'); return false;"><span class="icon">History</span></button></td>
                                                        <td>
                                                            <s:url action="petReport" var="rep">
                                                                <s:param name="petId" value="#pet.petId"/>
                                                            </s:url> 
                                                            <s:a href="%{#rep}"><s:property value="#pet.name"/></s:a>
                                                        </td>
							<td><s:property value="#pet.breed"/></td>
							<td><s:property value="#pet.color"/></td>
							<td><s:property value="#pet.sex"/></td>
							<td><s:date name="#pet.dateOfBirth" format="MM/dd/yyyy"/></td>
							<!--<td><button class="btn btn-primary btn-block btn-sm" onClick="window.open('statistics.jsp'); return false;"><span class="glyphicon glyphicon-stats"></span></button></td>-->
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:if>	
			
			
			
			
			
					<%--
					<div class="panel panel-default">
						<div class="panel-body">
							<center>
							<s:form action="addPet" theme="bootstrap" cssClass="form" method="POST">
							<s:hidden name="id" value="%{#session.currentRecord.controlNumber}"/>
							<p><s:textfield name="dateinput" label="Date:" id="apppicker" value="" placeholder="click here to set date"/></p>
							<p><s:textarea name="comment" label="Description:" cols="22" value=""/></p>
							</center>
						</div>
						<div class="panel-footer">
							<center>
								<s:submit cssClass="btn btn-primary" value="+ Appointment" />
							</center>
						</div>											
							</s:form>
					</div><!-END OF ROW->

			

			--%>

			 
		</div>

	</body>
</html>
