<%-- 
    Document   : index
    Created on : 01 31, 17, 1:57:06 PM
    Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

        <title>RADTECH PARS</title>
    </head>
    <body>

        <div class="container-fluid">
            <br><br><br><br>

            <center>
                <h1>Animal Station And Veterinary Clinic</h1>

                <h2>by RadTech</h2>
                <br/><br/><br/><br/>
                  
                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#popUpWindow">Login</button>
                <br/><br/>
                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#popUpWindow2">Sign up</button>
                  
              
                <!--POP UP WINDOW FOR LOGIN-->

                <div class="modal fade" id="popUpWindow">
                    <div class="modal-dialog">
                        <div class="modal-content">


                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h3 class="modal-title">LOGIN</h3>
                            </div>

                            <s:form action="login" theme="bootstrap">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <s:textfield name="user1" placeholder="Username" class="form-control" /></div>
                                    <div class="form-group">  
                                        <s:password name="pass1" placeholder="Password" class="form-control" /></div>
                                    <div class="modal-footer form-group" >

                                        <s:submit cssClass="btn btn-primary btn-block" value="LOGIN" />
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                </div> 

                <!--POP UP WINDOW FOR SIGN UP-->

                <div class="modal fade" id="popUpWindow2">
                    <div class="modal-dialog">
                        <div class="modal-content">


                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h3 class="modal-title">SIGN UP</h3>
                            </div>

                            <s:form action="signup" theme="bootstrap" >
                                <div class="modal-body">
                                    <div class="form-group">
                                        <s:textfield name="firstname" placeholder="Firstname"/>
                                    </div>
                                    <div class="form-group">
                                        <s:textfield name="lastname" placeholder="Lastname"/>
                                    </div>
                                    <div class="form-group">
                                        <s:textfield name="user2" placeholder="Username"/>
                                    </div>
                                    <div class="form-group">
                                        <s:textfield name="pass2" placeholder="Password"/>
                                    </div>
                                    <div class="modal-footer form-group" >
                                        <s:submit cssClass="btn btn-primary btn-block" value="SIGN UP" />
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                </div>
            </center>

        </div>      
    </body>
</html>
