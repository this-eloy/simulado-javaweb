package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Statement;

public class UsuarioDAO extends DataAccessObject <Usuario> {

    @Override
    public void insert(Usuario t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO usuario (id, nome, senha, tipo_usuario_id, nascimento, logradouro, numero, bairro, cidade, estado, uf, cep, convenio_id, especialidade_id, expediente_inicio, expediente_fim) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        preparedStatement.setString( 2, t.getNome() );
        preparedStatement.setString( 3, t.getSenha() );
        preparedStatement.setInt( 4, t.getTipoUsuarioId() );
        
        preparedStatement.setString( 5, t.getNascimento() );
        preparedStatement.setString( 6, t.getLogradouro() );
        preparedStatement.setString( 7, t.getNumero() );
        preparedStatement.setString( 8, t.getBairro() );
        preparedStatement.setString( 9, t.getCidade() );
        preparedStatement.setString( 10, t.getEstado() );
        preparedStatement.setString( 11, t.getUf() );
        preparedStatement.setString( 12, t.getCep() );
        
        preparedStatement.setObject( 13, t.getConvenioId() );
        
        preparedStatement.setObject( 14, t.getEspecialidadeId() );
        preparedStatement.setObject( 15, t.getExpedienteInicio() );
        preparedStatement.setObject( 16, t.getExpedienteFim() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(Usuario t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE usuario SET nome = ?, senha = ?, tipo_usuario_id = ?, nascimento = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, uf = ?, cep = ?, convenio_id = ? especialidade_id = ?, expediente_inicio = ?, expediente_fim = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getSenha() );
        preparedStatement.setInt(3, t.getTipoUsuarioId() );
        
        preparedStatement.setString( 4, t.getNascimento() );
        preparedStatement.setString( 5, t.getLogradouro() );
        preparedStatement.setString( 6, t.getNumero() );
        preparedStatement.setString( 7, t.getBairro() );
        preparedStatement.setString( 8, t.getCidade() );
        preparedStatement.setString( 9, t.getEstado() );
        preparedStatement.setString( 10, t.getUf() );
        preparedStatement.setString( 11, t.getCep() );
        
        preparedStatement.setObject( 12, t.getConvenioId() );
        
        preparedStatement.setObject( 13, t.getEspecialidadeId() );
        preparedStatement.setObject( 14, t.getExpedienteInicio() );
        preparedStatement.setObject( 15, t.getExpedienteFim() );
        
        preparedStatement.setInt( 16, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void delete(Usuario t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM usuario WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public Usuario getUnique(Object... values) throws Exception {
        
        Usuario resultado = null;
        
        String dql = "SELECT * FROM usuario WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Usuario( (int) resultSet.getObject(1) );
            
            resultado.setNome( (String) resultSet.getObject(2) );
            resultado.setSenhaHash( (String) resultSet.getObject(3) );
            resultado.setTipoUsuarioId( (int) resultSet.getObject(4) );
            
            if( resultSet.getObject(5) != null ) {
                resultado.setNascimento( resultSet.getObject(5).toString() ); // 'nascimento date' na tabela (tratar da mesma forma para outros campos não 'varchar', como 'hour'
            }
            
            resultado.setLogradouro( (String) resultSet.getObject(6) );
            resultado.setNumero( (String) resultSet.getObject(7) );
            resultado.setBairro( (String) resultSet.getObject(8) );
            resultado.setCidade( (String) resultSet.getObject(9) );
            resultado.setEstado( (String) resultSet.getObject(10) );
            resultado.setUf( (String) resultSet.getObject(11) );
            resultado.setCep( (String) resultSet.getObject(12) );

            if( resultSet.getObject(13) == null )  {
                resultado.setConvenioId( null );
            } else {
                resultado.setConvenioId( (int) resultSet.getObject(13) );
            }
            
            if( resultSet.getObject(14) == null )  {
                resultado.setEspecialidadeId( null );
            } else {
                resultado.setEspecialidadeId( (int) resultSet.getObject(14) );
            }
            
            if( resultSet.getObject(15) != null ) {
                resultado.setExpedienteInicio( resultSet.getObject(15).toString() ); 
            }
            if( resultSet.getObject(16) != null ) {
                resultado.setExpedienteFim( resultSet.getObject(16).toString() ); 
            }   
            
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

    @Override
    public ArrayList<Usuario> getAll() throws Exception {
        
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM usuario";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Usuario usuario = new Usuario( (int) resultSet.getObject( 1 ) );
            usuario.setNome( (String) resultSet.getObject( 2 ) );
            usuario.setSenhaHash( (String) resultSet.getObject( 3 ) );
            usuario.setTipoUsuarioId( (int) resultSet.getObject(4) );
            
            if( resultSet.getObject(5) != null ) {
                usuario.setNascimento( resultSet.getObject(5).toString() ); // 'nascimento date' na tabela (tratar da mesma forma para outros campos não 'varchar', como 'hour'
            }
            
            usuario.setLogradouro( (String) resultSet.getObject(6) );
            usuario.setNumero( (String) resultSet.getObject(7) );
            usuario.setBairro( (String) resultSet.getObject(8) );
            usuario.setCidade( (String) resultSet.getObject(9) );
            usuario.setEstado( (String) resultSet.getObject(10) );
            usuario.setUf( (String) resultSet.getObject(11) );
            usuario.setCep( (String) resultSet.getObject(12) );
            
            if( resultSet.getObject(13) == null )  {
                usuario.setConvenioId( null );
            } else {
                usuario.setConvenioId( (int) resultSet.getObject(13) );
            }
            
            if( resultSet.getObject(14) == null )  {
                usuario.setEspecialidadeId( null );
            } else {
                usuario.setEspecialidadeId( (int) resultSet.getObject(14) );
            }
            
            if( resultSet.getObject(15) != null ) {
                usuario.setExpedienteInicio( resultSet.getObject(15).toString() ); 
            }
            if( resultSet.getObject(16) != null ) {
                usuario.setExpedienteFim( resultSet.getObject(16).toString() ); 
            }   
            
            resultado.add(usuario);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}