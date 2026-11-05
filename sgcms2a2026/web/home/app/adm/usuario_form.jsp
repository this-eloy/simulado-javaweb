<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario" %>
<%@page import="model.UsuarioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuário</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Usuario us = null;
            String id = request.getParameter("id");
            if( id != null ) {
                us = new UsuarioDAO().getUnique( Integer.parseInt( id ) );
                
                if( us != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Usuário</h1>
        
        <form action="/sgcms2a2026/home?task=usuario&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= us != null ? us.getId() : "" %>" required <%= us != null ? "readonly" : "" %>> <br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= us != null ? us.getNome() : "" %>"><br/>
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" value="<%= us != null ? us.getSenha() : "" %>" required><br/>
            
            <label for="tipo_usuario_id">Tipo Usuário Id:</label>
            <input type="text" pattern="[0-9]*" id="tipo_usuario_id" name="tipo_usuario_id" value="<%= us != null ? us.getTipoUsuarioId() : "" %>" required> <br/>
            
            
            <label for="nascimento">Nascimento:</label>
            <input type="date" id="nascimento" name="nascimento" value="<%= ( us != null ) && ( us.getNascimento() != null ) ? us.getNascimento() : "" %>"><br/>
            
            <label for="logradouro">Logradouro:</label>
            <input type="text" id="logradouro" name="logradouro" value="<%= ( us != null ) && ( us.getLogradouro() != null ) ? us.getLogradouro() : "" %>"><br/>
            
            <label for="numero">Número:</label>
            <input type="text" id="numero" name="numero" value="<%= ( us != null ) && ( us.getNumero() != null ) ? us.getNumero() : "" %>"><br/>
            
            <label for="bairro">Bairro:</label>
            <input type="text" id="bairro" name="bairro" value="<%= ( us != null ) && ( us.getBairro() != null ) ? us.getBairro() : "" %>"><br/>
            
            <label for="nome">Cidade:</label>
            <input type="text" id="cidade" name="cidade" value="<%= ( us != null ) && ( us.getCidade() != null ) ? us.getCidade() : "" %>"><br/>
            
            <label for="estado">Estado:</label>
            <input type="text" id="estado" name="estado" value="<%= ( us != null ) && ( us.getEstado() != null ) ? us.getEstado() : "" %>"><br/>
            
            <label for="uf">UF:</label>
            <input type="text" id="uf" name="uf" value="<%= ( us != null ) && ( us.getUf() != null ) ? us.getUf() : "" %>"><br/>
            
            <label for="cep">CEP:</label>
            <input type="text" id="cep" name="cep" value="<%= ( us != null ) && ( us.getCep() != null ) ? us.getCep() : "" %>">
            <input type="button" value="Buscar Endereço" name="buscar_endereco" onclick="buscar_endereco_cep()" />
            <br/>
            
            <label for="convenio_id">Convênio Id:</label>
            <input type="text" pattern="[0-9]*" id="convenio_id" name="convenio_id" value="<%= ( us != null ) && ( us.getConvenioId() != null ) ? us.getConvenioId() : "" %>"> <br/>
            
            <label for="especialidade_id">Especialidade Id:</label>
            <input type="text" pattern="[0-9]*" id="especialidade_id" name="especialidade_id" value="<%= ( us != null ) && ( us.getEspecialidadeId() != null ) ? us.getEspecialidadeId() : "" %>"> <br/>
            
            <label for="expediente_inicio">Expediente Início:</label>
            <input type="time" id="expediente_inicio" name="expediente_inicio" value="<%= ( us != null ) && ( us.getExpedienteInicio() != null ) ? us.getExpedienteInicio() : "" %>"><br/>
            
            <label for="expediente_fim">Expediente Fim:</label>
            <input type="time" id="expediente_fim" name="expediente_fim" value="<%= ( us != null ) && ( us.getExpedienteFim() != null ) ? us.getExpedienteFim() : "" %>"><br/>
            
            
            <input type="submit" value="Salvar">
            
        </form>
            
        <script>
            
            function buscar_endereco_cep() {
                
                const xhr = new XMLHttpRequest();
                
                const regexCEP = /^\d{5}-\d{3}$/;
                
                var cep = document.getElementById("cep").value;
                
                if( cep !== "" ) {                    
                    if( regexCEP.test(cep) == true )  {
                        
                        var url = "https://viacep.com.br/ws/" + cep.replace('-', '') + "/json/";

                        xhr.open( "GET", url );
                        xhr.send();

                        xhr.onreadystatechange = function() {
                            
                            if ( xhr.readyState === 4 && xhr.status === 200 ) {

                                var endereco_json = JSON.parse(xhr.responseText);
                                
                                document.getElementById("logradouro").value = endereco_json["logradouro"];
                                document.getElementById("bairro").value = endereco_json["bairro"];
                                document.getElementById("cidade").value = endereco_json["localidade"];
                                document.getElementById("uf").value = endereco_json["uf"];
                                document.getElementById("estado").value = endereco_json["estado"];
                                
                            }
                        };
                        
                    } else {
                        alert("CEP deve estar no formato DDDDD-DDD");
                        document.getElementById("cep").value = "";
                    }                    
                }
                
            }
            
        </script>
        
    </body>
</html>
