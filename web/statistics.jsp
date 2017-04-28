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
		</style>
		<title>Animal Station Statistics</title>
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/themes/fusioncharts.theme.fint.js"></script>
                <script src="js/jquery-1.12.4.js"></script>
                <script src="js/jquery-ui.js"></script>
		<script type="text/javascript">
                    FusionCharts.ready(function() {
                        var fusioncharts = new FusionCharts({
                            type: 'column2d',
                            renderAt: 'chartContainer',
                            width: '500',
                            height: '300',
                            dataFormat: 'jsonurl',
                            dataSource: 'stat.json'
                        });
                        fusioncharts.render();
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
<!--			<div class="row">
				<s:form action="viewStat" theme="bootstrap" cssClass="form">
					<div class="col-md-3"></div>
					<div class="col-md-2"><s:textfield name="dateInput1" id="date1" placeholder="From" /></div>
					<div class="col-md-2"><s:textfield name="dateInput2" id="date2" placeholder="To" /></div>
					<div class="col-md-1"><s:select cssClass="text text-block" name="type" list="{'Breeds','Appointments','Walk ins','Medicines','Cancels'}" headerKey="-1"/></div>
					<div class="col-md-1"><s:submit cssClass="btn btn-primary btn-xs" value="View" /></div>
				</s:form>
			</div>-->
			<center>
			<h1><font face="roboto">Animal Station Statistics</font></h1>
	
	
			<div id="chartContainer">FusionCharts XT will load here!
				<script>
                                    fusioncharts.render();
				</script>
			</div>
			</center>
		</div>	
	</body>
</html>
