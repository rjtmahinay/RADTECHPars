<%-- 
	Document   : accounts
	Created on : Mar 7, 2017, 10:40:41 PM
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
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
				<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<title>Account Settings</title>
	</head>
	<body>
		<div class="container">
			<s:include value="home.jsp"/>
			<h1>Manage Accounts</h1>
                        <tr>
                            <td>Currently saved Security Questions</td>
                            <script>
                                alert('<s:property value="%{#session.currentUser.name}"/>');
                            </script>
                        </tr>
			<div id="actionTabs" class="container-fluid">
				<center>
					<ul class="nav nav-pills">    
						<li class="active">
							<a href="#1b" data-toggle="tab">Change Password</a>
						</li>
						<li>
							<a href="#2b" data-toggle="tab">Create New User</a>
						</li>
						<li>
							<a href="#3b" data-toggle="tab">Additional Security</a>
						</li>
					</ul>
				</center>	
				<div class="tab-content clearfix">
					<div class="tab-pane active" id="1b">

						<s:actionerror/>
                                                <s:form action="changePassword" theme="bootstrap" cssClass="form" method="POST">

							<br/>
							<div class="form-group">
								<s:password name="password" placeholder="Current Password" class="form-control" /></div>
							<div class="form-group">  
								<s:password name="password2" placeholder="New Password" class="form-control" /></div>
							<div class="form-group">  
								<s:password name="password3" placeholder="Confirm New Password" class="form-control" /></div>


							<s:submit cssClass="btn btn-primary " value="submit" />


						</s:form>

					</div> <!end of tab 1->
					<div class="tab-pane" id="2b">
						<s:actionerror/>
						<s:form action="signup" theme="bootstrap" method="POST">

							<br/>
							<div class="form-group">
								<s:textfield name="name" placeholder="Firstname" />
							</div>
							<div class="form-group">
								<s:textfield name="surname" placeholder="Lastname"/>
							</div>
							<div class="form-group">
								<s:textfield name="username" placeholder="Username"/>
							</div>
							<div class="form-group">
								<s:password name="password" placeholder="Password"/>
							</div>
							<div class="form-group">
								<s:password name="password2" placeholder="Confirm Password"/>
							</div>

							<s:submit cssClass="btn btn-primary btn" value="sign up" />


						</s:form>
					</div>
						
					<div class="tab-pane" id="3b">
						
						
						<!--WALA PANG ACTION-->
						<br>
						<s:form action="addQuestion" theme="bootstrap" method="POST">
                                                    <div class="form-group">
                                                        <s:hidden name="username" value="%{#session.currentUser.username}"/>
                                                        <s:textfield name="question" placeholder="Enter secret question here" />
                                                    </div>
                                                    <div class="form-group">

                                                        <s:password name="answer" placeholder="Enter answer here" />
                                                    </div>
                                                    <div class="form-group">

                                                        <s:password name="password" placeholder="Enter password" />
                                                    </div>
                                                    <s:submit cssClass="btn btn-primary" value="submit" />
						</s:form>
					</div>
						
				</div>
			</div>
		</div>			
	</body>
</html>
