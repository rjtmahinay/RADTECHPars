<%-- 
	Document   : statistics
	Created on : 09-Mar-2017, 20:39:43
	Author     : Sphere
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fusioncharts.FusionCharts" %>
<%@page import="java.util.*" %>
<%@page import="com.google.gson.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
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
			tr {
			width: 100%;
			display: inline-table;
			/*height:50px;*/
			table-layout: fixed;

			}
			table{
				width:px;
				height:500px;
			}
			
			tbody{
			  overflow-y: scroll;
			  width:460px;
			  height: 460px;
			  width: 95%;
			  position: absolute;
			}
			
		</style>
		<title>Animal Station Statistics</title>
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/themes/fusioncharts.theme.fint.js"></script>
                <script src="js/jquery-1.12.4.js"></script>
                <script src="js/jquery-ui.js"></script>
		<script type="text/javascript">
                    
                    FusionCharts.ready(function() {
                        var fusioncharts = new FusionCharts({
                            type: '${type}',
                            renderAt: 'chartContainer',
                            width: '650px',
                            height: '500px',
                            dataFormat: 'jsonurl',
                            dataSource: 'stat.json'
                        });
                        
                        var tempWeight = new FusionCharts({
                            type: 'zoomline',
                            renderAt: 'tempWeightContainer',
                            width: '250px',
                            height: '250px',
                            yaxisminValue: "0",
                            yaxismaxValue: "120",
                            pixelsPerPoint: "0",
                            pixelsPerLabel: "30",
                            lineThickness: "1",
                            compactdatamode: "1",
                            dataseparator: "|",
                            dataFormat: 'jsonurl',
                            dataSource: 'tempweight.json'
                        });
                        
                        var medicineChart = new FusionCharts({
                            type: 'column2d',
                            renderAt: 'medicineChartContainer',
                            width: '250px',
                            height: '250px',
                            dataFormat: 'jsonurl',
                            dataSource: 'petMeds.json'
                        });
                               
                        var visitChart = new FusionCharts({
                            type: 'column2d',
                            renderAt: 'visitChartContainer',
                            width: '250px',
                            height: '250px',
                            dataFormat: 'jsonurl',
                            dataSource: 'petVisits.json'
                        });
                        fusioncharts.render();
                        tempWeight.render();
                        medicineChart.render();
                        visitChart.render();
                    });       
                    
                    
                    $( function() {
                        $( "#date1" ).datepicker();
                      } );

                      $( function() {
                        $( "#date2" ).datepicker();
                      } );

</script>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">

			
<!--			<h1><font face="roboto">Animal Station Statistics</font></h1>-->
					<s:if test="%{#session.display.equals('breed')}">
						<div class="row">
							<div class="col-md-6">
								<div class="table table-responsive">
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<th width="5%">#</th>
											<th width="30.5%">Customer Name</th>
											<th width="31%">Pet Name</th>
											<th>Breed</th>

										</thead>
										<tbody>
											<s:iterator value="#session.reports" var="stats" status="count">
											<tr>
												<td width="5%"><s:property value="#count.count"/></td>
												<td><s:property value="#stats.S2"/></td>
												<td><s:property value="#stats.S1"/></td>
												<td><s:property value="#stats.S3"/></td>
											</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
							</div>
							<div class="col-md-6">
								<div id="chartContainer">FusionCharts XT will load here!
									<script>
										fusioncharts.render();
									</script>
								</div>
							</div>
						</div>	
					</s:if>
					
					<s:if test="%{#session.display.equals('status')}">
						<div class="row">
							<div class="col-md-6">
								<div class="table table-responsive">
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<th width="5%">#</th>
											<th width="30.5%">Date</th>
											<th width="31%">Customer Name</th>
											<th>Status</th>



										</thead>
										<tbody>
											<s:iterator value="#session.reports" var="stats" status="count">
											<tr>
												<td width="5%"><s:property value="#count.count"/></td>
												<td><s:date name="#stats.D1" format="MM/dd/yyyy"/></td>
												<td><s:property value="#stats.S1"/></td>
												<td><s:property value="#stats.S2"/></td>


											</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
							</div>
							<div class="col-md-6">
								<div id="chartContainer">FusionCharts XT will load here!
									<script>
										fusioncharts.render();
									</script>
								</div>
							</div>	
						</div>
					</s:if>
					
					<s:if test="%{#session.display.equals('appointments')}">
						<div class="row">
							<div class="col-md-6">
								<div class="table table-responsive">
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<th width="5%">#</th>
											<th width="30.5%">Date</th>
											<th width="31%">Customer Name</th>
											<th>Pet Name</th>

										</thead>
										<tbody>
											<s:iterator value="#session.reports" var="stats" status="count">
											<tr>
												<td width="5%"><s:property value="#count.count"/></td>
												<td><s:property value="#stats.S2"/></td>
												<td><s:property value="#stats.S1"/></td>
												<td><s:property value="#stats.S3"/></td>
											</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
							<div class="col-md-6">
								<div id="chartContainer">FusionCharts XT will load here!
									<script>
										fusioncharts.render();
									</script>
								</div>
							</div>
						</div>
					</s:if>
					
					<s:if test="%{#session.display.equals('walk-in')}">
						<div class="row">
							<div class="col-md-6">
								<div class="table table-responsive">
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<th width="5%">#</th>
											<th width="30.5%">Customer Name</th>
											<th width="31%">Address</th>
											<th>Contact Number</th>


										</thead>
										<tbody>
											<s:iterator value="#session.reports" var="stats" status="count">
											<tr>
												<td width="5%"><s:property value="#count.count"/></td>
												<td><s:property value="#stats.S1"/></td>
												<td><s:property value="#stats.S2"/></td>
												<td><s:property value="#stats.S3"/></td>
											</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
							</div>
							<div class="col-md-6">
								<div id="chartContainer">FusionCharts XT will load here!
									<script>
										fusioncharts.render();
									</script>
								</div>
							</div>
						</div>	
					</s:if>
					
					<s:if test="%{#session.display.equals('medicine')}">
						<div class="row">
							<div class="col-md-6">
								<div class="table table-responsive">
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<th width="5%">#</th>
											<th width="30.5%">Customer Name</th>
											<th width="31%">Pet Name</th>
											<th>Medicine</th>


										</thead>
										<tbody>
											<s:iterator value="#session.reports" var="stats" status="count">
											<tr>
												<td width="5%"><s:property value="#count.count"/></td>
												<td><s:property value="#stats.S1"/></td>
												<td><s:property value="#stats.S2"/></td>
												<td><s:property value="#stats.S3"/></td>
											</tr>
											</s:iterator>
										</tbody>

									</table>
								</div>
							</div>	
							<div class="col-md-6">
								<div id="chartContainer">FusionCharts XT will load here!
									<script>
										fusioncharts.render();
									</script>
								</div>
							</div>
						</div>	
					</s:if>
					<s:if test="%{#session.display.equals('pet')}">
						<div class="modal fade" id="confirmDelete">
							<s:form action="deletePet">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h3 align="center" class="modal-title">Delete Pet?</h3>
									</div>
									<div class="modal-body">
										<p align="center">Delete pet from owner?</p>
									</div>
									<div class="modal-footer form-group" >
										<div class="row">
											<div class="col-md-6 col-sm-6">
												<input type="hidden" id="delete" name="input3"/>
												<center><s:submit type="button" cssClass="btn btn-danger btn-block" value="Delete" /></center>
											</div>
											<div class="col-md-6 col-sm-6">
													<center><button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">Back</button></center>
											</div>

										</div>
									</div>
								</div>
							</div>
							</s:form>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div id="sourceTable">
									<div class="table table-responsive">
										<table class="table table-striped table-hover table-bordered">
											<thead>
												<th width="19.5%">Appointment ID</th>
												<th width="19.5%">Date</th>
												<th width="19.5%">Weight</th>
												<th width="19.5%">Temperature</th>
												<th width="">Medicines Given</th>
											</thead>
											<tbody>
												<s:iterator value="#session.report" var="report">
													<tr>
														<td><s:property value="#report.Id1"/></td>
														<td><s:date name="#report.D1" format="MM/dd/yyyy"/></td>
														<td><s:property value="#report.S1"/></td>
														<td><s:property value="#report.S2"/></td>
														<td><s:property value="#report.S3"/></td>
													</tr>
												</s:iterator>
											</tbody>	
										</table>
									</div>	
								</div>
							</div>
							<div class="col-md-3">
								<div id="tempWeightContainer">
									<script>
										tempWeight.render();
									</script>
								</div>
									<br>

								<div id="visitChartContainer">
									<script>
										visitChart.render();
									</script>
								</div>
							</div>
							<div class="col-md-2">
								<div id="medicineChartContainer">
									<script>
										medicineChart.render();
									</script>
								</div>
								
							</div>
							<div class="col-md-1">
								<p align="right">
								<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDelete" 
								data-id="<s:property value="%{#record.appointmentId}"/>"><span class="glyphicon glyphicon-trash"></span></button>
							</div>
							
								
							
						</div>	
					</s:if>

				
				
				
				
				
				
				</div>
				
				
<%--END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF --%>					
<%--END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF --%>					
<%--END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF END OF FIRST HALF --%>					

					
			
			
	</body>
</html>
