package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Statement;

public class ConvenioDAO extends DataAccessObject <Convenio> {

    @Override
    public void insert(Convenio t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "INSERT INTO convenio (id, nome, cnpj, telefone, valor) values (?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 1, t.getId() );
        preparedStatement.setString( 2, t.getNome() );
        preparedStatement.setString( 3, t.getCnpj() );
        preparedStatement.setString( 4, t.getTelefone() );       
        preparedStatement.setDouble( 5, t.getValor() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void update(Convenio t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "UPDATE convenio SET nome = ?, cnpj = ?, telefone = ?, valor = ? WHERE id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(dml);
        
        preparedStatement.setInt( 5, t.getId() );
        preparedStatement.setString( 1, t.getNome() );
        preparedStatement.setString( 2, t.getCnpj() );
        preparedStatement.setString( 3, t.getTelefone() );       
        preparedStatement.setDouble( 4, t.getValor() );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        preparedStatement.execute();
        
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
    }

    @Override
    public void delete(Convenio t) throws Exception {
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        String dml = "DELETE FROM convenio WHERE id = ?";
        
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
    public Convenio getUnique(Object... values) throws Exception {
        
        Convenio resultado = null;
        
        String dql = "SELECT * FROM convenio WHERE id = ?";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(dql);
        
        preparedStatement.setInt( 1,  (int) values[0] );
        
        if( AppConfig.getInstance().getConfig("settings", "verbose").equals("true") ) {
            System.out.println(preparedStatement);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean status = resultSet.next();
        
        if( status == true ) {
            
            resultado = new Convenio( (int) resultSet.getObject(1) );
            
            resultado.setNome( (String) resultSet.getObject(2) );
            resultado.setCnpj( (String) resultSet.getObject(3) );
            resultado.setTelefone( (String) resultSet.getObject(4) );
                        
            if( resultSet.getObject(5) != null ) {
                float aux = (float) resultSet.getObject(5);
                resultado.setValor( aux );
            }
            
        }
        
        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

    @Override
    public ArrayList<Convenio> getAll() throws Exception {
        
        ArrayList<Convenio> resultado = new ArrayList<>();
        
        String dql = "SELECT * FROM convenio";
        
        Connection connection = DataBaseConnections.getInstance().getConnection();
        
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dql);
        
        while( resultSet.next() ) {
            
            Convenio convenio = new Convenio( (int) resultSet.getObject( 1 ) );
            
            convenio.setNome( (String) resultSet.getObject(2) );
            convenio.setCnpj( (String) resultSet.getObject(3) );
            convenio.setTelefone( (String) resultSet.getObject(4) );
            
            if( resultSet.getObject(5) != null ) {
                float aux = (float) resultSet.getObject(5);
                convenio.setValor( aux );
            }
            
            resultado.add(convenio);
            
        }
        
        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        
        return resultado;
        
    }

}