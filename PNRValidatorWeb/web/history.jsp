<%-- 
    Document   : history
    Created on : 2020-01-25, 02:25:43
    Author     : Daniel-PC
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History of verifications</title>
    </head>
    <body>
    <center>
        <%  String aboutDatabase = (String) session.getAttribute("aboutDatabase");
        if(aboutDatabase !=null)
        {
            %><h1><%= aboutDatabase %></h1><%
        }
        %>
            
        Your history of verifications:
        <br>
        <%
            List<String> history = (List<String>) session.getAttribute("history");
            if(history != null)
            {
            for(String verification: history)
            {%>
               <%= verification %> <br/>
            <%}
            }
            %>
    </center>
    
    <br>
    
    <center><button onclick="goBack()">Go Back</button></center>
    
    
    <script>function goBack() {window.history.back()}</script>
    
    </body>
</html>
