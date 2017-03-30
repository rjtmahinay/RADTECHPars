<%-- 
		Document   : welcome
		Created on : Feb 28, 2017, 3:20:14 PM
		Author     : Carl
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow-x:hidden;" >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
                <script src="js/jquery-1.12.4.js"></script>
                <script src="js/jquery-ui.js"></script>
		<style>
		<%--	
			body
		{
			overflow-x:hidden;
		}
		.table-fixed th {
		width:100%;
		float:left;
		height:50px;
		border-bottom-width: 0;
		}	
		.table-fixed thead {
		width: 97%;
		}
		.table-fixed tbody {
		  height: 440px;
		  overflow-y: auto;
		  overflow-x:hidden;
		  width: 101.25%;
		}
		.table-fixed tbody, .table-fixed tr, .table-fixed td {
		  display: block;
		}
		 .table-fixed tbody td, .table-fixed thead > tr >th{
		  width:8.33%;
		  height:50px;
		  float: left;
		  border-bottom-width: 0;
		}
		

			table {
				border-collapse: collapse;
				width: 100%;
			}
			tbutton
			{
				width:10%;
			}
			
			thead, tbody{ display: block; }

			tbody {
				width:100%;
				height: 450px;       
				overflow-y: auto;    /* Trigger vertical scroll    */
				
			} --%>
		</style>

		<script>
			$('form').each(function () {
				this.reset();
			});
                        
                        $(document).ready(function() {
                        $('#confirmArchive').on('show.bs.modal', function(e) {
                          var id = $(e.relatedTarget).data('id');
                          document.getElementById('arcinput').value = id;
                          alert(id);
                        });
                    });  
                    
                   
                   $( function() {
                    $( "#apppicker" ).datepicker();
                   } );
		</script>
		<title>Welcome To Radtech PARS</title>
	</head>
	<body>
		<s:include value="home.jsp"/>

		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2 col-sm-2">
					<br>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRecord"><span class="glyphicon glyphicon-plus"></span></button>
				</div>
				<div class="col-md-8">
					<center>
					<h1><font face="roboto">All Records</font></h1>
					</center>
				</div>
				<div class="col-md-2"></div>
			</div>
                        <div class="modal fade" id="confirmArchive">
                            <s:form action="toArchive">
                                <input type="hidden" name="idinput" value="" id="arcinput"/>
                            <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                            <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h3 align="center" class="modal-title">Confirm Archive</h3>
                                            </div>
                                            <div class="modal-body">
                                                    <p align="center">Reason for archive:</p>
                                                    <center><s:textfield name="reason"/></center>

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
			<div class="modal fade" id="addRecord">
				<div class="modal-dialog">
					<div class="modal-content">
						<center>

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">Add Record</h3>
							</div>

							<!-INSERT FORM ACTION HERE->


							<s:form action="addRecord" theme="bootstrap" cssClass="form" method="POST">
								<div class="modal-body">


									<div class="table">
									<table class="table table-condensed">
										<tr>
											<td><b>Owner Name:</b></td>
											<td><s:textfield name="ownerName" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Address:</b></td>
											<td><s:textfield name="address" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Contact Number:</b></td>
											<td><s:textfield name="contactNumber" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Pet Name:</b></td>
											<td><s:textfield name="patientName" placeholder=""/></td>
										</tr>

										<tr>
											<td><b>Sex:</b></td>
											<td><s:select cssClass="text text-block" name="sex" list="{'', 'Male','Female'}" headerKey="-1"/></td>
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
											<td><b>Date of birth:</b></td>
											<td>
												<s:textfield name="dateinput" id="datepicker" />
                                                                                                <script>
                                                                                                $( function() {
                                                                                                    $( "#datepicker" ).datepicker();
                                                                                                } );
                                                                                                </script>
											</td>
										</tr>
										
										
										
										<tr>
											<td><b>Weight:</b></td>
											<td><s:textfield name="weight" placeholder=""/></td>
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


			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse table-fixed">
					<thead>
						<tr>
							<th>Update</th>
							<th>Archive</th>    
							<th>Record #</th>                                
							<th>Owner Name</th>
							<th>Address</th>
							<th>Contact #</th>
							<th>Pet Name</th>
							<th>Sex</th>
							<th>Breed</th>
							<th>Color</th>
							<th>Date of Birth</th>
							<th>Weight (kg)</th>
							
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="#session.view" var="record">
							<tr style="page-break-inside: avoid;">
								<s:url action="getRecord" var="rec">
									<s:param name="id"><s:property value="#record.controlNumber"/></s:param>
								</s:url>
								<td>       
									<s:a href="%{rec}"><button class="btn btn-block btn-primary" type="submit" name="action">Update</button></s:a>
								</td>

								<td>
									<button type="button" data-id="<s:property value="#record.controlNumber"/>" class="btn btn-danger btn-block" 
                                                                        
                                                                        data-toggle="modal" data-target="#confirmArchive">Archive</button>

									

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
