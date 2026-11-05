package controller;

import  framework.config.AppConfig;
import  framework.log.ExceptionLogTrack;
import  framework.dao.DataBaseConnections;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class OuvinteContexto implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println( AppConfig.getInstance() );
        System.out.println( ExceptionLogTrack.getInstance() );
        System.out.println( DataBaseConnections.getInstance() );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DataBaseConnections.getInstance().closeAllConnections();
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
        }
    }
}
