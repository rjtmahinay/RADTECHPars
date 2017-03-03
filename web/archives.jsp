<%-- 
    Document   : archives
    Created on : Mar 2, 2017, 11:09:08 PM
    Author     : Carl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>Archives</title>
    </head>
    <body>
        <s:include value="home.jsp"/>
        <h1>Archives</h1>
        <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped table-inverse" align="center">
                    <thead>
                        <tr>
                            <th>Restore</th>
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
                                    <button class="btn btn-block btn-warning" type="submit" name="action" value="restore">Restore</button>
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
                        <%--
                        <s:iterator value="#session.view" var="record">
                            
                                
                            <tr>
                                <td>
                                    <input type="hidden" name="controlNumber" value="" >         
                                    <button class="btn btn-block btn-warning" type="submit" name="action" value="restore">Restore</button>
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
                        </s:iterator>--%>

                        <!-INSERT TABLE BODY HERE->
                        

                    </tbody>
                </table>
            </div>
    </body>
</html>
