<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Especialidade" %>
<%@page import="model.EspecialidadeDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Especialidade</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Especialidade esp = null;
            String id = request.getParameter("id");
            if( id != null ) {
                esp = new EspecialidadeDAO().getUnique( Integer.parseInt( id ) );
                
                if( esp != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Especialidade</h1>
        
        <form action="/sgcms2a2026/home?task=especialidade&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= esp != null ? esp.getId() : "" %>" required <%= esp != null ? "readonly" : "" %>> <br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= esp != null ? esp.getNome() : "" %>" required ><br/>
            
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="<%= ( esp != null ) && ( esp.getDescricao() != null ) ? esp.getDescricao() : "" %>"><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>
