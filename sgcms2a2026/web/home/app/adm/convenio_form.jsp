<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Convenio" %>
<%@page import="model.ConvenioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Convênio</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Convenio cv = null;
            String id = request.getParameter("id");
            if( id != null ) {
                cv = new ConvenioDAO().getUnique( Integer.parseInt( id ) );
                
                if( cv != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Convênio</h1>
        
        <form action="/sgcms2a2026/home?task=convenio&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= cv != null ? cv.getId() : "" %>" required <%= cv != null ? "readonly" : "" %>> <br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= cv != null ? cv.getNome() : "" %>" required ><br/>
            
            <label for="cnpj">CNPJ:</label>
            <input type="text" id="cnpj" name="cnpj" value="<%= ( cv != null ) && ( cv.getCnpj() != null ) ? cv.getCnpj() : "" %>"><br/>
            
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" value="<%= ( cv != null ) && ( cv.getTelefone() != null ) ? cv.getTelefone() : "" %>"><br/>
            
            <label for="valor">Valor:</label>
            <input type="number" min="0.00" step="0.01" placeholder="0.00" id="valor" name="valor" value="<%= ( cv != null ) ? cv.getValor() : "" %>" required><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>
