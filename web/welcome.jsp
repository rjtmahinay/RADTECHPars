<%-- 
    Document   : welcome
    Created on : Feb 28, 2017, 3:20:14 PM
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
        
        <title>Welcome To Radtech PARS</title>
    </head>
    <body>
        <s:include value="home.jsp"/>
        
        <div class="container-fluid">
            <center>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRecord">Add Record</button>
            </center>
            
            <div class="modal fade" id="addRecord">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <center>

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="modal-title">Add Record</h3>
                        </div>
                            
                            <!-INSERT FORM ACTION HERE->
                            
                            
                        <s:form action="" theme="bootstrap" cssClass="form">
                            <div class="modal-body">
                                
                                <div class="form-group">
                                    <label for="ownerName">Owner Name</label>
                                    <s:textfield name="ownerName" placeholder=""/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <s:textfield name="address" placeholder=""/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="contactNumber">Contact Number</label>
                                    <s:textfield name="contactNumber" placeholder=""/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="patienttName">Pet Name</label>
                                    <s:textfield name="patienttName" placeholder=""/>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="sex">Sex</label>
                                    <s:select cssClass="text text-block" name="sex" list="{'', 'Male','Female'}" headerKey="-1"/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="breed">Breed</label>
                                    <s:select name="breed" list="{'', 'Beagle', 'Great Dane'}" headerKey="-1"/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="age">Age</label>
                                    <s:textfield name="age" placeholder=""/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="weight">Weight</label>
                                    <s:textfield name="weight" placeholder=""/>
                                </div>
                                
                                
                                
                                
                                    
                                <div class="modal-footer form-group" >
                                    <center>
                                    <s:submit cssClass="btn btn-primary btn-block" value="Add Record" />
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
                <table class="table table-bordered table-hover table-striped table-inverse" align="center">
                    <thead>
                        <tr>
                            <th>Update</th>
                            <th>Archive</th>    
                            <th>#</th>                                
                            <th>Owner Name</th>
                            <th>Address</th>
                            <th>Contact Number</th>
                            <th>Pet Name</th>
                            <th>Sex</th>
                            <th>Breed</th>
                            <th>Age</th>
                            <th>Weight</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                                <td>
                                    <input type="hidden" name="controlNumber" value="" >         
                                    <button class="btn btn-block btn-primary" type="submit" name="action" value="update1">Update</button>
                                </td>
                    
                                <td>
                                    <input type="hidden" name="controlNumber" value="" >         
                                    <button class="btn btn-block btn-danger" type="submit" name="action" value="delete">Archive</button>
                                </td>
                                <td>sample</td>
                                <td>sample</td>
                                <td>sample</td>
                                <td>2</td>
                                <td>sample</td>
                                <td>sample</td>
                                <td>sample</td>
                                <td>2</td>
                                <td>2</td>
                                
                                
                                
                            </tr>
                        
                        <s:iterator value="#session.view" var="record">
                            
                                
                            <tr>
                                <td>
                                    <input type="hidden" name="controlnum" value="${item.control_number}" >         
                                    <button class="btn btn-block btn-primary" type="submit" name="action" value="update1">Update</button>
                                </td>
                    
                                <td>
                                    <input type="hidden" name="controlnum" value="${item.control_number}" >         
                                    <button class="btn btn-block btn-danger" type="submit" name="action" value="delete">Archive</button>
                                </td>
                                <s:url action="getRecord" var="rec">
                                    <s:param name="id"><s:property value="#record.controlNumber"/></s:param>
                                </s:url>
                                
                                <td><s:a href="%{rec}"> <s:property value="#record.controlNumber"/> </s:a> </td>
                                <td><s:property value="#record.ownerName" /></td>
                                <td><s:property value="#record.address" /></td>
                                <td><s:property value="#record.contactNumber" /></td>
                                <td><s:property value="#record.patientName" /></td>
                                <td><s:property value="#record.sex" /></td>
                                <td><s:property value="#record.breed" /></td>
                                <td><s:property value="#record.age" /></td>
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
