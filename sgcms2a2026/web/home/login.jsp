<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        
        <% if( request.getAttribute("msg") != null ) { %>
            <script>
                alert("<%= (String) request.getAttribute("msg") %>");
            </script>
        <% } %>
        
        <%
            String id = "";
            Cookie[] cookies = request.getCookies();
            if( cookies != null ) {
                for( Cookie cookie : cookies ) {
                    if( cookie.getName().equals("id") ) {
                        id = cookie.getValue();
                    }
                }
            }
        %>
        <h1>Login</h1>
        <form action="/sgcms2a2026/home?task=login" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= id %>" required> <br/>

<!--            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= id %>" required> <br/>-->
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" value="" required><br/>
            
            <input type="submit" value="Login">
            
        </form>
    </body>
</html>
