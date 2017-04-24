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
		<link rel="stylesheet" href="css/jquery-ui.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>Account Settings</title>
	</head>
	<body>
		<div class="container">
			<s:include value="home.jsp"/>
			<h1>Manage Accounts</h1>

			<div id="actionTabs" class="container-fluid">

					<ul class="nav nav-pills">    
						<li class="active">
							<a href="#1b" data-toggle="tab">Change Password</a>
						</li>
						<s:if test="%{#session.currentUser.userType.equals('doctor') | #session.currentUser.userType.equals('admin')}">
						<li>
							<a href="#2b" data-toggle="tab">Create New User</a>
						</li>
						</s:if>
					</ul>

				<div class="tab-content clearfix">
					<div class="tab-pane active" id="1b">
                                            <s:form action="changePassword" theme="bootstrap" cssClass="form" method="POST">

                                                <br/>
                                                <div class="form-group">
                                                    <s:hidden name="username" value="%{#session.currentUser.username}"/>
                                                    <s:password name="password" placeholder="Current Password" class="form-control" /><s:actionerror/></div>
                                                    <div class="form-group">  
                                                    <s:password name="newPassword" placeholder="New Password" class="form-control" /><s:actionerror/></div>
                                                    <div class="form-group">  
                                                    <s:password name="confirmPassword" placeholder="Confirm New Password" class="form-control" /></div>
                                                <s:submit cssClass="btn btn-primary " value="submit" />
                                            </s:form>

					</div> <!end of tab 1->
					<div class="tab-pane" id="2b">
                                            <s:form action="signup" theme="bootstrap" method="POST">
                                                <s:hidden name="userType" value="#session.currentUser.userType"/>
                                                <br/>
                                                <div class="form-group">
                                                        <s:textfield name="name" placeholder="Firstname" /><s:actionerror/>
                                                </div>
                                                <div class="form-group">
                                                        <s:textfield name="username" placeholder="Username"/><s:actionerror/>
                                                </div>
                                                <div class="form-group">
                                                        <s:password name="password" placeholder="Password"/><s:actionerror/>
                                                </div>
                                                <div class="form-group">
                                                        <s:password name="confirmPassword" placeholder="Confirm Password"/>
                                                </div>
                                                <div class="form-group">
                                                        <s:textfield name="securityQuestion" placeholder="Security Question"/><s:actionerror/>
                                                </div>
                                                <div class="form-group">
                                                        <s:password name="securityAnswer" placeholder="Security Answer"/><s:actionerror/>
                                                </div>

                                                <s:submit cssClass="btn btn-primary btn" value="sign up" />
                                            </s:form>
					</div>
					<%--	
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
					--%>
				</div>
			</div>
		</div>			
	</body>
</html>
