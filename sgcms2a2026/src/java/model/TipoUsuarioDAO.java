package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoUsuarioDAO extends DataAccessObject <TipoUsuario> {

    @Override
    public void insert(TipoUsuario t) throws Exception {
       
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO tipo_usuario (id, modulo_administrativo, modulo_agendamento, modulo_atendimento) values (?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        preparedStatement.setString( 2, t.getModuloAdministrativo() );
        preparedStatement.setString( 3, t.getModuloAgendamento() );
        preparedStatement.setString( 4, t.getModuloAtendimento() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(TipoUsuario t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE tipo_usuario SET modulo_administrativo = ?, modulo_agendamento = ?, modulo_atendimento = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 4, t.getId() );
        preparedStatement.setString( 1, t.getModuloAdministrativo() );
        preparedStatement.setString( 2, t.getModuloAgendamento() );
        preparedStatement.setString( 3, t.getModuloAtendimento() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void delete(TipoUsuario t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM tipo_usuario WHERE id = ?";
        
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
    public TipoUsuario getUnique(Object... values) throws Exception {
        
        TipoUsuario resultado = null;
        
        String dql = "SELECT * FROM tipo_usuario WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new TipoUsuario( (int) resultSet.getObject(1) );
            
            resultado.setModuloAdministrativo( (String) resultSet.getObject(2) );
            resultado.setModuloAgendamento( (String) resultSet.getObject(3) );
            resultado.setModuloAtendimento( (String) resultSet.getObject(4) );
            
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

    @Override
    public ArrayList<TipoUsuario> getAll() throws Exception {
        
        ArrayList<TipoUsuario> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM tipo_usuario";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            TipoUsuario tipoUsuario = new TipoUsuario( (int) resultSet.getObject( 1 ) );
            
            tipoUsuario.setModuloAdministrativo( (String) resultSet.getObject(2) );
            tipoUsuario.setModuloAgendamento( (String) resultSet.getObject(3) );
            tipoUsuario.setModuloAtendimento( (String) resultSet.getObject(4) );
            
            resultado.add(tipoUsuario);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}