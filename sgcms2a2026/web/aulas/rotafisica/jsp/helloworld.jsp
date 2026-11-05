<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelloWorld JSP</title>
    </head>
    
    <%
       ArrayList<String> cores = new ArrayList();
       
       cores.add("azul");
       cores.add("verde");
       cores.add("amarelo");
       cores.add("branco");
    %>
    
    <%
        int a = 4;
        int b = 2;
        out.print( a / b );
    %>
    
    <body>
        
        <h1>HelloWorld JSP</h1>
        <h1><%= a%></h1>
        <h1><%= b%></h1>
        <h1><%= a / b%></h1>
        
        <h1><%= request %></h1>
        <h1><%= response %></h1>
        <h1><%= application %></h1>
        <h1><%= session %></h1>
        
        <ul>
            <% for( String s : cores) { %>
                <li><%= s%></li>
            <% } %>
        </ul>

        
    </body>
</html>
