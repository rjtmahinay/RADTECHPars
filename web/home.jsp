<%-- 
		Document   : home
		Created on : 01 31, 17, 9:03:37 PM
		Author     : Aspire
--%>

<%@taglib uri="/struts-tags" prefix="s" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		
		
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-1.12.4.js"></script>
		<script src="js/jquery-ui.js"></script>
		<script src="js/bootstrap.min.js"></script>
		
		<script>
			$( function() {
			  $( "#date1" ).datepicker({maxDate: "+0M +0D", changeMonth: true, changeYear: true});
			} );

			$( function() {
			  $( "#date2" ).datepicker({maxDate: "+1M +0D", changeMonth: true, changeYear: true});
			} );

		</script>
		
		
		
		
		<title>RADTECH PARS</title>
	</head>


	<body>

		<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">


			<!-- Logo -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="schedule.jsp" class="navbar-brand">Animal Station <span class="glyphicon glyphicon-home"></span></a>
			</div>

			<!-- Menu Items -->
			<div class="collapse navbar-collapse" id="mainNavBar">
				
				<ul class="nav navbar-nav">
					<s:if test="%{#session.currentUser.userType.equals('assistant')}">
						<li><a href="search.jsp">Profiles <span class="glyphicon glyphicon-user"></span></a></li>
						<li><a href="add.jsp">New Customer <span class="glyphicon glyphicon-plus"></span></a></li>
					</s:if>
					<s:if test="%{#session.currentUser.userType.equals('doctor')}">
						<li><a href="search.jsp">Profiles <span class="glyphicon glyphicon-user"></span></a></li>
						<%--<li><s:a href="statistics.jsp">Statistics <span class="glyphicon glyphicon-stats"></span></s:a></li>--%>
						<li><a href="#" data-toggle="modal" data-target="#stats">Statistics <span class="glyphicon glyphicon-stats"></span></a></li>
						<li><a href="archives.jsp">Archives <span class="glyphicon glyphicon-trash"></span></a></li>
					</s:if>	
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><s:a href="accounts.jsp"><s:property value="%{#session.currentUser.userType}"/> <s:property value="%{#session.currentUser.name}"/>  <span class="glyphicon glyphicon-cog"></span> </s:a></li>
					<s:url action='logout' var="logout"/>
					<li><s:a href="%{logout}"> Logout <span class="glyphicon glyphicon-log-in"/></s:a></li>
				</ul>        

			</div>
		</nav> 
		<div class="container-fluid">
			<div class="modal fade" id="stats">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<center>
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">Select Statistics Range and Value</h3>
							</div>
							<s:form action="statize" theme="bootstrap" cssClass="form">
								<div class="modal-body">
<!--									
									
									-->
									<div class="form-group">
										<s:textfield name="dateInput2" id="date1" placeholder="From" />
									</div>
									<div class="form-group">
										<s:textfield name="dateInput3" id="date2" placeholder="To" />
									</div>
									<div class="form-group">
										<s:select cssClass="text text-block" name="statType" list="{'Breed','Appointment','Walk-in','Medicines','Status'}" headerKey="-1"/>
									</div>
									<div class="form-group">
										<s:submit cssClass="btn btn-primary btn-sm" value="View" />
									</div>
								</div>
							</s:form>
						</center>  
					</div>
				</div>
			</div>
			<br>
			<br>
			<br>
		</div>   
	</body>
</html>
