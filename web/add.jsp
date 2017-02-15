<%-- 
    Document   : add
    Created on : 02 6, 17, 8:21:56 PM
    Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>RADTECH PARS</title>
    </head>
    
    <body>
        
        <s:include value="home.jsp" />   
    
    <div class="container-fluid">
        
        
        
        <%--     <form action="LoginController" method="POST">    
            <div class="col-md-12 col-sm-12">
            <table class="table table-condensed">
                <tr>
                    <td>Owner's Name:</td> 
                    <td><input type="text" name="ownerName" required></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address" required></td>
                </tr>
                <tr>
                    <td>Contact Number:</td>
                    <td><input type="text" name="contactNumber" required></td>
                </tr>
                <tr>
                    <td>Patient's Name:</td>
                    <td><input type="text" name="patientName" required></td>
                </tr>
                <tr>
                    <td>Breed:</td>
                    <td><input type="text" name="breed" required></td>
                </tr>
                <tr>
                    <td>Sex:</td>
                    <td><input type="text" name="sex" required></td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><input type="text" name="age" required></td>
                </tr>
                <tr>
                    <td>Color:</td>
                    <td><input type="text" name="color" required></td>
                </tr>
                <tr>
                    <td>Weight:</td>
                    <td><input type="text" name="weight" required></td>
                </tr>
            
        
        
            </table>
            </div>
            
            <p align="center"><button name="action" value="add" class="btn btn-primary" type="submit">Add</button>
            <button class="btn btn-secondary" type="reset">Clear</button>
            </form>    --%>    
               
        <s:form action="info">
            <div class="col-md-12 col-sm-12">
            <table class="table table-condensed">
                <tr>
                    <td><s:textfield label="Owner Name" name="ownerName"/></td>
                </tr>
                <tr>
                    <td><s:textfield label="Address" name="address"/></td>
                </tr>
                <tr>
                    <td><s:textfield label="Contact Number" name="contactNumber"/></td>
                </tr>
                <tr>
                    <td><s:textfield label="Patient Name" name="patientName"/></td>
                </tr>
                <tr>
                    <td><s:textfield label="Age" name="age"/></td>
                </tr>
                <tr>
                    <td><s:textfield label="Weight" name="weight"/></td>
                </tr>
            </table>
                
                <center>
                    <s:submit value="Add" cssClass="btn btn-primary"/>
                    <s:reset value="clear" cssClass="btn btn-secondary"/>
                </center>
            </div>   
        </s:form>
        
        <s:form action="home.jsp">
            <center>
                <s:submit value="Back" cssClass="btn btn-secondary"/>
            </center>
        </s:form>
           <%--     <form action="LoginController" method="POST">
                    <center><button class="btn btn-secondary" name="action" value="viewall" type="submit">Back</button></center>
                </form> --%>
                 
        </div>    
    </body>
</html>
