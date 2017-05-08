<%-- 
	Document   : diagnoHistory
	Created on : Apr 18, 2017, 8:02:21 PM
	Author     : Carl
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
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
		<title>Diagnosis History</title>
	</head>
	<body>
		<script>
		$('.panel-collapse').collapse({toggle: false});
			$('body').on('click', '[data-toggle=collapse-next]', function (e) {
				e.preventDefault();

				// Try to close all of the collapse areas first
				var parent_id = $(this).data('parent');
//				$(parent_id+' .panel-collapse').collapse('hide');

				// ...then open just the one we want
				var $target = $(this).parents('.panel').find('.panel-collapse');
				$target.collapse('toggle');
			});
		</script>
		
		<div class="container-fluid">
			<br>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2">Consultation Date</div>
						<div class="col-md-5">DIagnosis</div>
						<div class="col-md-5">Medicines Given</div>
					</div>
				</div>
			</div>
			
			<s:iterator value="%{#session.consultations}" var="cons">
			
			<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a class="accordion-toggle" data-toggle="collapse-next" data-parent="#accordion">
							<div class="row">
								<div class="col-md-2"><s:date name="#cons.consultationDate" format="MM/dd/yyyy"/></div>
								<div class="col-md-5"><s:property value="#cons.diagnosis"/></div>
								<div class="col-md-5"><s:property value="#cons.medicines"/></div>
							</div>
						</a>
					</h4>
				</div>
				<div class="panel-collapse collapse">
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<thead>
									<th>Temp.</th>
									<th>Weight</th>
									<th>Eyes</th>
									<th>Ears</th>
									<th>Nose</th>
									<th>Throat</th>
									<th>Derma</th>
									<th>Gums</th>
									<th>Nodes</th>
								</thead>
								<tbody>
									<tr>
										<td><s:property value="#cons.temperature"/></td>
										<td><s:property value="#cons.weight"/></td>
										<td><s:property value="#cons.eyes"/></td>
										<td><s:property value="#cons.ears"/></td>
										<td><s:property value="#cons.nose"/></td>
										<td><s:property value="#cons.throat"/></td>
										<td><s:property value="#cons.derma"/></td>
										<td><s:property value="#cons.gums"/></td>
										<td><s:property value="#cons.lymphNodes"/></td>
									</tr>
								</tbody>
							</table>
						
						</div>
									
					</div>
									
				</div>
									
			</div>	
			</s:iterator>
			</div>
		</div>
	</body>
</html>
