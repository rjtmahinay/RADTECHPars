<%-- 
	Document   : add
	Created on : 02 6, 17, 8:21:56 PM
	Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-ui.js"></script>	
		<script src="js/jquery-1.12.4.js"></script>
	
	<script src="js/bootstrap.min.js"></script>
	
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
	<script>
		
	$( function() {
	  $("#petDateOfBirth").datepicker( {maxDate: "+0M +0D", changeMonth: true, changeYear: true});
	} );
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
		<title>Add new customers here</title>
	</head>

	<body>
		<div class="container-fluid">
		<s:include value="home.jsp"/>
		<h1>New Customer</h1>
		<h1>Pet List  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPet"><span class="glyphicon glyphicon-plus"></span></button></h1>
				
			
		<div class="modal fade" id="addPet">
		<div class="modal-dialog">
			<div class="modal-content">
				<center>

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="modal-title">Add Record</h3>
					</div>

					<s:form action="tempet" theme="bootstrap" cssClass="form" method="POST">
						<div class="modal-body">
							<div class="panel panel-default">
							<div class="table">
							<table class="table table-condensed">
								<tr>
									<td><b>Pet Name:</b></td>
									<td><s:textfield name="name" placeholder=""/> <s:actionerror/></td>
								</tr>
								<tr>
									<td><b>Breed:</b></td>
									<td><s:textfield name="breed" id="breeds" placeholder=""/> <s:actionerror/></td>
								</tr>
								<tr>
									<td><b>Color:</b></td>
									<td><s:textfield name="color" placeholder=""/> <s:actionerror/></td>
								</tr>
								<tr>
									<td><b>Sex:</b></td>
									<td><s:select cssClass="text text-block" name="sex" list="{'Male','Female'}" headerKey="-1"/></td>
								</tr>
								<tr>
									<td><b>Date of birth:</b></td>
									<td>
									<s:textfield name="dateInput" id="petDateOfBirth" /> <s:actionerror/>
									</td>
								</tr>

							</table>
						</div>
						</div>			

							<div class="modal-footer form-group" >
								<center>
									<s:submit cssClass="btn btn-primary btn-block" value="submit" />
									<s:reset value="clear" cssClass="btn btn-block"/>
								</center>
							</div>
						</div>
					</s:form>


				</center>  
			</div>
		</div>
		</div>	

		<div class="table table-responsive">
		<table class="table table-striped table-bordered table-hover">
			<thead>

				<th>Name</th>
				<th>Breed</th>
				<th>Color</th>
				<th>Sex</th>
				<th>Date of Birth</th>
			</thead>
			<tbody>
				<s:iterator value="%{#session.tempets}" var="tempo">
				<tr>
					<td><s:property value="#tempo.name"/></td>
					<td><s:property value="#tempo.breed"/></td>
					<td><s:property value="#tempo.color"/></td>
					<td><s:property value="#tempo.sex"/></td>
                                        <td><s:date name="#tempo.dateOfBirth" format="MM/dd/yyyy"/></td>
				</tr>
				</s:iterator>

			</tbody>
		</table>
	</div>












	<%--			
			<s:form action="tempet" theme="bootstrap" cssClass="form" method="POST">
						<div class="modal-body">
							<div class="table">
							<table class="table table-condensed">
								<tr>
									<td><b>Pet Name:</b></td>
									<td><s:textfield name="name" placeholder=""/></td>
								</tr>
								<tr>
									<td><b>Breed:</b></td>
									<td><s:textfield name="breed" placeholder=""/></td>
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
									<td><b>Date of birth:</b></td>
									<td>
									<s:textfield name="dateInput" id="datepicker" />
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
	--%>
			<s:form action="addCustomer" theme="bootstrap" cssClass="form" method="POST">
				<label for="name">Customer Name</label>
				<s:textfield name="name" placeholder=""/><s:actionerror/>

				<label for="address">Address</label>
				<s:textfield name="address" placeholder=""/><s:actionerror/>

				<label for="contactNumber">Contact Number</label>
				<s:textfield name="contactNumber" placeholder=""/><s:actionerror/>

				<br>
				<div class="row">
					<div class="col-md-5"></div>
					<div class="col-md-1">	
						<s:reset value="Clear" cssClass="btn btn-secondary btn-block"/>
					</div>
					<div class="col-md-1">
						<p align="CENTER"><s:submit cssClass="btn btn-primary btn-block" value="Submit" /></p>
					</div>
					<div class="col-md-5"></div>
				</div>
			</s:form>
						
<!-END OF FIRST HALF OF ADD , END OF FIRST HALF OF ADD , END OF FIRST HALF OF ADD , END OF FIRST HALF OF ADD , END OF FIRST HALF OF ADD ,END OF FIRST HALF OF ADD ,->	
							
						
				
			
		
		
		
	<%--		
	   <s:form action="home.jsp">
		<center>
			<s:submit value="Back" cssClass="btn btn-secondary"/>
		</center>
	</s:form>
	<%--     <form action="LoginController" method="POST">
			 <center><button class="btn btn-secondary" name="action" value="viewall" type="submit">Back</button></center>
		 </form> 
	--%>
		 </div>
	</body>
</html>
