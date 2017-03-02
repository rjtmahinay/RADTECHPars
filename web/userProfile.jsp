<%-- 
    Document   : userProfile
    Created on : Feb 28, 2017, 4:03:36 PM
    Author     : Carl
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>User Profile</title>
    </head>
    <body>
        <s:include value="home.jsp"/>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-sm-12">
                    <h1>User Profile</h1>
                    <!-INSERT USER PROFILE HERE->
                    
                </div>
                
                <div class="col-md-6 col-sm-12">
                    <br>
                    <h2>Last Appointment</h2>
                    
                    
                    <h1>        <!-INSERT APPOINTMENT HERE->        </h1>
                    
                    
                    <br><br>
                    <h3>Next Scheduled Appointment</h3>
                    
                    
                    <h2>        <!-INSERT NEXT APPOINTMENT HERE->       </h2>
                    
                    
                </div>
            </div>              <!-END OF ROW->
            <center>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diagnoWindow">Add Diagnosis</button>
            </center>
            <div class="modal fade" id="diagnoWindow">
                <div class="modal-dialog">
                    <div class="modal-content">


                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="modal-title">Add Diagnosis</h3>
                        </div>

                        <s:form action="" theme="bootstrap" cssClass="form">
                            <div class="modal-body">
                                <div class="form-group">
                                    <s:textarea label="Diagnosis" name="diagnosis" cols="74" rows="4" class="form-control" />
                                </div>
                                
                                <div class="form-group"> 
                                    <s:textarea label="Comments" name="comments" cols="74" rows="3" class="form-control"/>
                                    
                                </div>
                                <div class="modal-footer form-group" >

                                    <s:submit cssClass="btn btn-primary btn-block" value="Add Diagnosis" />
                                </div>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
