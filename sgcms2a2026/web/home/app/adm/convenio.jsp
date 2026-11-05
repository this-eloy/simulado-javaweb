<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Convenio" %>
<%@page import="model.ConvenioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Convênios</title>
    </head>
    <body>
        
        <% 
            ArrayList<Convenio> lista = new ConvenioDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Convênios</h1>
        
        <table>
            
            <tr>
                <th>Id | </th>
                <th>Nome | </th>
                <th>CNPJ | </th>                
                <th>Telefone | </th>
                <th>Valor | </th>
                
                <th></th>
                <th></th>
            </tr>
            
            <% for( Convenio cv : lista ) { %>
                <tr>
                    
                    <td><%= cv.getId() %></td>
                    <td><%= cv.getNome() %></td>
                    
                    <td><%= cv.getCnpj() != null ? cv.getCnpj() : "-" %></td>
                    <td><%= cv.getTelefone() != null ? cv.getTelefone() : "-" %></td>
                    
                    <td><%= cv.getValor() %></td>
                    
                    <td><a href="/sgcms2a2026/home/app/adm/convenio_form.jsp?id=<%= cv.getId() %>">Alterar</a></td>
                    
                    <td><a href="/sgcms2a2026/home?task=convenio&action=delete&id=<%= cv.getId()%>" onclick="return confirm('Deseja realmente excluir Convênio <%= cv.getId() %> -- <%= cv.getNome() %>')" >Excluir</a></td>
                    
                </tr>
            <% } %>
            
        </table>
            
        <button onclick="window.location.href='/sgcms2a2026/home/app/adm/convenio_form.jsp'">Adicionar</button>
        
    </body>
</html>
