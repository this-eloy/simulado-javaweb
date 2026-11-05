<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.TipoUsuario" %>
<%@page import="model.TipoUsuarioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuários</title>
    </head>
    <body>
        
        <% 
            ArrayList<TipoUsuario> lista = new TipoUsuarioDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Tipo Usuários</h1>
        
        <table>
            
            <tr>
                <th>Id | </th>
                <th>Módulo Administrativo | </th>
                <th>Módulo Agendamento | </th>
                <th>Módulo Atendimento | </th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( TipoUsuario usTp : lista ) { %>
                <tr>
                    
                    <td><%= usTp.getId() %></td>
                    <td><%= usTp.getModuloAdministrativo()%></td>
                    <td><%= usTp.getModuloAgendamento()%></td>
                    <td><%= usTp.getModuloAtendimento()%></td>
                    
                    <td><a href="/sgcms2a2026/home/app/adm/tipousuario_form.jsp?id=<%= usTp.getId() %>">Alterar</a></td>
                    
                    <td><a href="/sgcms2a2026/home?task=tipousuario&action=delete&id=<%= usTp.getId()%>" onclick="return confirm('Deseja realmente excluir Tipo Usuário <%= usTp.getId() %>')" >Excluir</a></td>
                    
                </tr>
            <% } %>
        
        </table>
            
        <button onclick="window.location.href='/sgcms2a2026/home/app/adm/tipousuario_form.jsp'">Adicionar</button>
        
    </body>
</html>
