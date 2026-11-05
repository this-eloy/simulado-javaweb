package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Statement;

public class EspecialidadeDAO extends DataAccessObject <Especialidade> {

    @Override
    public void insert(Especialidade t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO especialidade (id, nome, descricao) values (?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        preparedStatement.setString( 2, t.getNome() );
        preparedStatement.setString( 3, t.getDescricao() );       
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(Especialidade t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE especialidade SET nome = ?, descricao = ?,  WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getDescricao() );
        
        preparedStatement.setInt( 3, t.getId() );
        
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void delete(Especialidade t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM especialidade WHERE id = ?";
        
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
    public Especialidade getUnique(Object... values) throws Exception {
        
        Especialidade resultado = null;
        
        String dql = "SELECT * FROM especialidade WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Especialidade( (int) resultSet.getObject(1) );
            
            resultado.setNome( (String) resultSet.getObject(2) );
            resultado.setDescricao( (String) resultSet.getObject(3) );       
            
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

    @Override
    public ArrayList<Especialidade> getAll() throws Exception {
        
        ArrayList<Especialidade> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM especialidade";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Especialidade especialidade = new Especialidade( (int) resultSet.getObject( 1 ) );
            
            especialidade.setNome( (String) resultSet.getObject(2) );
            especialidade.setDescricao( (String) resultSet.getObject(3) );    
            
            resultado.add(especialidade);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}