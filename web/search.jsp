<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>RADTECH PARS</title>
	</head>
	<body>
	   <s:include value="home.jsp" />
	   <div class="container">
			<s:form action="searchDatabase" namespace="/">
				<div class="row">
					<div class="col-md-3 col-sm-0"></div>

					<div class="col-md-2 col-sm-4">
						<center>
                                                    <s:select name="searchType" list="{'Control Number', 'Address', 'Age', 'Breed', 'Color', 'Contact Number', 'Owner Name', 'Patient Name', 'Sex', 'Weight'}" 
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
							<s:submit cssClass="btn btn-primary btn-sm" name="submit" value="submit"/>
						</center>
					</div>
					<div class="col-md-3 col-sm-0"></div>
				</div>         
			</s:form>
		</div> 
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
						<th>Age</th>
						<th>Weight</th>
					</tr>
				</thead>

				<tbody>

					<s:iterator value="#session.search" var="record">
						<tr>
								<td>
									<input type="hidden" name="controlnum" value="${item.control_number}" >         
									<button class="btn btn-block btn-primary" type="submit" name="action" value="update1">Update</button>
								</td>

								<td>
									<input type="hidden" name="controlnum" value="${item.control_number}" >         
									<button class="btn btn-block btn-danger" type="submit" name="action" value="delete">Delete</button>
								</td>
								<s:url action="getRecord" var="rec">
									<s:param name="id"><s:property value="#record.controlNumber"/></s:param>
								</s:url>

								<td><s:a href="%{rec}"> <s:property value="#record.controlNumber"/> </s:a> </td>
								<td><s:property value="#record.ownerName" /></td>
								<td><s:property value="#record.address" /></td>
								<td><s:property value="#record.contactNumber" /></td>
								<td><s:property value="#record.patientName" /></td>
								<td><s:property value="#record.sex" /></td>
								<td><s:property value="#record.breed" /></td>
								<td><s:property value="#record.age" /></td>
								<td><s:property value="#record.weight" /></td>
							</tr>
						</s:iterator>
				</tbody>    
			</table>
		</div>    


	</body>
</html>
