package framework.dao;

import framework.config.AppConfig;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnections {
    
    private static DataBaseConnections singleton;
    private ArrayList<Connection> pool;

    private DataBaseConnections() {
        pool = new ArrayList<>();
    }
    
    public static DataBaseConnections getInstance() {
        if( singleton == null ) {
            singleton = new DataBaseConnections();
        }
        return singleton;
    }
    
    public synchronized Connection getConnection() throws SQLException {
        Connection connection =  DriverManager.getConnection( 
                 AppConfig.getInstance().getConfig("database", "url"), 
                 AppConfig.getInstance().getConfig("database", "user"), 
                 AppConfig.getInstance().getConfig("database", "password") );
         
        pool.add(connection);
         
        return connection;                 
    }
    
    public synchronized void closeConnection(Connection connection) throws SQLException {
        
        if( connection != null ) {
            
            if( pool.contains(connection) ) {
                pool.remove(connection);
            }
            
            if( connection.isClosed() == false ) {
                connection.close();
            }
            
        }
        
    }
    
    public synchronized void closeAllConnections() throws SQLException {
        
        for( Connection connection : pool ) {
            
            if( ( connection != null ) &&
                    ( connection.isClosed() == false ) ) {
                    connection.close();
            }
            
        }
        
        pool.clear();             
        
    }

}