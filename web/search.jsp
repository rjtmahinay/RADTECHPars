<%-- 
    Document   : search
    Created on : 02 7, 17, 1:41:17 PM
    Author     : Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RADTECH PARS</title>
    </head>
    <body>
       <s:include value="home.jsp" />
       <div class="container-fluid">
            <s:form action="searchDatabase" namespace="/">
               <h1 align="left">
                    <s:select headerKey="-1" headerValue="Select Search Type"
                    list="#application.searchlist"
                    name="searchType"/>
                    <s:textfield name="searchInput" />
                </h1>
                   <s:submit name="submit" value="submit"/>
            </s:form>
        </div> 
        
        <s:iterator value="#session.search" var="record">
            Inside if
            <s:property value="controlNumber"/>
        </s:iterator>
            <s:property value="searchInput"/>
            <s:property value="#session.trial"/>

       
    </body>
</html>
