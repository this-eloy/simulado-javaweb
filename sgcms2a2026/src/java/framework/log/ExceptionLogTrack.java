package framework.log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExceptionLogTrack {
    
    private final String fileName;
    private Document doc;
    private Element root;
    
    // Padrão de Deskign Orientado a Objetos Singleton (instância única)
    private static ExceptionLogTrack singleton;
    
    private ExceptionLogTrack() {
        fileName = "./exception-log-track.xml";
        openFile();
    }
    
    public static ExceptionLogTrack getInstance() {
        if( singleton == null ) {
            singleton = new ExceptionLogTrack();
        }
        return singleton;
    }
    
    // Padrão de Design Orientado a Objetos Estrutural: Facade (Fachada para Composição/Operação Complexa).
    private void openFile() {
        
        try {
        
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            File file = new File(fileName);
            if( file.exists() ) {
                
                doc = docBuilder.parse( file );
                root = doc.getDocumentElement();
                
            } else {
                
                doc = docBuilder.newDocument();
                root = doc.createElement( "logs" );
                doc.appendChild(root);
                
            }
        
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private void saveFile() {
        try {
            
            FileOutputStream output = new FileOutputStream(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            String xsl = "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:output method=\"xml\" omit-xml-declaration=\"yes\" indent=\"yes\"/><xsl:strip-space elements=\"*\"/><xsl:template match=\"node()|@*\"><xsl:copy><xsl:apply-templates select=\"node()|@*\"/></xsl:copy></xsl:template></xsl:stylesheet>";
            InputStream inputStream = new ByteArrayInputStream( xsl.getBytes(StandardCharsets.UTF_8) );
            Source xslt = new StreamSource( inputStream );
            Transformer transformer = transformerFactory.newTransformer(xslt);

            DOMSource source = new DOMSource( doc );
            StreamResult result = new StreamResult( output );

            transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );
            transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
            transformer.setOutputProperty( OutputKeys.ENCODING,"UTF-8" );
            transformer.transform( source, result );
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void addLog(Exception ex) {
        
        Element log = doc.createElement("exception");
        
        log.setAttribute( "date", new Date().toString() );
        log.setAttribute( "class", ex.getClass().toString() );
        log.setAttribute( "message", ex.getMessage() );
        
        root.appendChild(log);
        
        saveFile();
                
    }

}