package framework.config;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AppConfig {
    
    private Element configElement;    
    
    // Padrão de Design Orientado a Objetos Criacional: Singleton (Instância Unica).
    private static AppConfig singleton;
    
    private AppConfig() {
        
        try {
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document document;
            
            File file = new File("./app-config.xml");
            
            if( file.exists() ) {
                
                document = docBuilder.parse( new File("./app-config.xml") );
                
            } else {
                
                document = docBuilder.parse( Thread.currentThread().getContextClassLoader().getResourceAsStream("./framework/config/app-config.xml") );
                
            }
            
            document.getDocumentElement().normalize();
            
            configElement = document.getDocumentElement();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static AppConfig getInstance() {
        if( singleton == null ) {
            singleton = new AppConfig();
        }
        return singleton;            
    }
    
    public String getConfig(String tagName, String attributeName) {
        Node node = configElement.getElementsByTagName(tagName).item(0);
        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }

}