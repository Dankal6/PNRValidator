<%-- 
    Document   : aboutVerification
    Created on : 2020-01-25, 01:45:34
    Author     : Daniel-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About verification</title>
    </head>
    <body>
    
        <%
            String aboutVarification = (String) session.getAttribute("aboutVarification");
            String aboutDatabase = (String) session.getAttribute("aboutDatabase");
            Integer counter = (Integer) session.getAttribute("counter");
        %>
    <center> 
        <h1><%= aboutVarification %></h1>
        <%
            if(aboutDatabase !=null)
            {
                %><h1><%= aboutDatabase %></h1><%
            }
            %>
        
        <%= "Verifications: " + counter %>
    </center>
    
    <br>
    
    <center><button onclick="goBack()">Go Back</button></center>
    
    
    <script>function goBack() {window.history.back()}</script>
    
    </body>
</html>
