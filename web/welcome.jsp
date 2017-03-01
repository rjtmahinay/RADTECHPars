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
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        
        <title>Welcome To Radtech PARS</title>
    </head>
    <body>
        <s:include value="home.jsp"/>
        <br><br>
        <h1>welcome to the new home page</h1>
        <div class="container-fluid">
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped" align="center">
                    <thead>
                        <tr>
                            <th>Update</th>
                            <th>Delete</th>
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
                                    <button type="submit" name="action" value="update1">Update</button>
                                </td>
                    
                                <td>
                                    <input type="hidden" name="controlNumber" value="" >         
                                    <button type="submit" name="action" value="delete">Delete</button>
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
                                    <button type="submit" name="action" value="update1">Update</button>
                                </td>
                    
                                <td>
                                    <input type="hidden" name="controlnum" value="${item.control_number}" >         
                                    <button type="submit" name="action" value="delete">Delete</button>
                                </td>
                                <td><s:property value="#record.controlNumber" /></td>
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
