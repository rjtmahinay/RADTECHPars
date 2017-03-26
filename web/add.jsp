<%-- 
	Document   : add
	Created on : 02 6, 17, 8:21:56 PM
	Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>RADTECH PARS</title>
	</head>

	<body>
		<div class="container-fluid">
			


		<s:include value="home.jsp"/>
		
		<h2>Add Record</h2>
		
		
		<s:form action="addRecord" theme="bootstrap" cssClass="form" method="POST">
			

				
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
				






				<center>
					<s:submit cssClass="btn btn-primary " value="Add Record" />
					<s:reset value="clear data" cssClass="btn btn-secondary"/>
				</center>

			
		</s:form>

	  <%--  <s:form action="home.jsp">
		<center>
			<s:submit value="Back" cssClass="btn btn-secondary"/>
		</center>
	</s:form>
	<%--     <form action="LoginController" method="POST">
			 <center><button class="btn btn-secondary" name="action" value="viewall" type="submit">Back</button></center>
		 </form> --%>
	
		 </div>
	</body>
</html>
