<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Especialidade" %>
<%@page import="model.EspecialidadeDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Especialidades</title>
    </head>
    <body>
        
        <% 
            ArrayList<Especialidade> lista = new EspecialidadeDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Especialidades</h1>
        
        <table>
            
            <tr>
                <th>Id | </th>
                <th>Nome | </th>
                <th>Descrição | </th>                             
                
                <th></th>
                <th></th>
            </tr>
            
            <% for( Especialidade esp : lista ) { %>
                <tr>
                    
                    <td><%= esp.getId() %></td>
                    <td><%= esp.getNome() %></td>
                    
                    <td><%= esp.getDescricao() != null ? esp.getDescricao() : "-" %></td>

                    
                    <td><a href="/sgcms2a2026/home/app/adm/especialidade_form.jsp?id=<%= esp.getId() %>">Alterar</a></td>
                    
                    <td><a href="/sgcms2a2026/home?task=especialidade&action=delete&id=<%= esp.getId()%>" onclick="return confirm('Deseja realmente excluir Especialidade? <%= esp.getId() %> -- <%= esp.getNome() %>')" >Excluir</a></td>
                    
                </tr>
            <% } %>
            
        </table>
            
        <button onclick="window.location.href='/sgcms2a2026/home/app/adm/especialidade_form.jsp'">Adicionar</button>
        
    </body>
</html>
