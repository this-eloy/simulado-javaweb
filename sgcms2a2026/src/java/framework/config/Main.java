package framework.config;

public class Main {
    public static void main(String[] args) {
        
        System.out.println( AppConfig.getInstance().getConfig("settings", "verbose") );
        
        System.out.println( AppConfig.getInstance().getConfig("database", "url") );
        System.out.println( AppConfig.getInstance().getConfig("database", "user") );
        System.out.println( AppConfig.getInstance().getConfig("database", "password") );
        
    }
}