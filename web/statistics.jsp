<%-- 
	Document   : statistics
	Created on : 09-Mar-2017, 20:39:43
	Author     : Sphere
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
		<title>JSP Page</title>
		<script type="text/javascript" src="js/fusioncharts.js"></script>
		<script type="text/javascript" src="js/themes/fusioncharts.theme.fint.js"></script>
		<script type="text/javascript">
			var digits = [].concat(('<s:property value="%{#session.scores}"/>').trim().split(","));

  FusionCharts.ready(function(){
	var revenueChart = new FusionCharts({
		"type": "column2d",
		"renderAt": "chartContainer",
		"width": "90%",
		"height": "400",
		"dataFormat": "json",
		"dataSource":  {
		  "chart": {
			"caption": "Monthly visits",
			"subCaption": "Animal Station",
			"xAxisName": "Month",
			"yAxisName": "Number of Appointments",
			"theme": "fint"
		 },
		 "data": [
			{
			   "label": "Jan",
			   "value": digits[0]
			},
			{
			   "label": "Feb",
			   "value": digits[1]
			},
			{
			   "label": "Mar",
			   "value": digits[2]
			},
			{
			   "label": "Apr",
			   "value": digits[3]
			},
			{
			   "label": "May",
			   "value": digits[4]
			},
			{
			   "label": "Jun",
			   "value": digits[5]
			},
			{
			   "label": "Jul",
			   "value": digits[6]
			},
			{
			   "label": "Aug",
			   "value": digits[7]
			},
			{
			   "label": "Sep",
			   "value": digits[8]
			},
			{
			   "label": "Oct",
			   "value": digits[9]
			},
			{
			   "label": "Nov",
			   "value": digits[10]
			},
			{
			   "label": "Dec",
			   "value": digits[11]
			}
		  ]
	  }

  });
revenueChart.render();
})


</script>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
			<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			<script>
			$( function() {
			  $( "#date1" ).datepicker();
			} );
			
			$( function() {
			  $( "#date2" ).datepicker();
			} );
			</script>
			<div class="row">
				<s:form action="viewStat" theme="bootstrap" cssClass="form">
					<div class="col-md-3"></div>
					<div class="col-md-2"><s:textfield name="dateInput1" id="date1" placeholder="From" /></div>
					<div class="col-md-2"><s:textfield name="dateInput2" id="date2" placeholder="To" /></div>
					<div class="col-md-1"><s:select cssClass="text text-block" name="type" list="{'Breeds','Appointments','Walk ins','Medicines','Cancels'}" headerKey="-1"/></div>
					<div class="col-md-1"><s:submit cssClass="btn btn-primary btn-xs" value="View" /></div>
				</s:form>
			</div>
			<center>
			<h1><font face="roboto">Animal Station Statistics</font></h1>
	
	
			<div id="chartContainer">FusionCharts XT will load here!
				<script>
				revenueChart.render();
				</script>
			</div>
			</center>
		</div>	
	</body>
</html>
