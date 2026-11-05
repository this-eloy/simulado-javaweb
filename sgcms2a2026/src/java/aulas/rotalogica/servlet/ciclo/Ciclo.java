package aulas.rotalogica.servlet.ciclo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "Ciclo", urlPatterns = {"/aulas/rotalogica/servlet/ciclo"})
public class Ciclo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        /*
        Inicializar da Servelet.
        
        Criação e leitura das variáveis comuns para todos os tipos de requisição/chamadas (verbos) HTTP.
        
        Utilizado para inicializar recursos.
        */
        super.init();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Recebe requisições/chamadas HTTP.
        
        Distingue e encaminha as diversas requisições/chamadas (verbos) HTTP, como doGet(...) ...
        */
        System.out.println("\n\nTESTE\n\n");
        
        Enumeration headerNames = req.getHeaderNames();
        while( headerNames.hasMoreElements() ) {
            String headerName = headerNames.nextElement().toString();
            System.out.println( headerName + " : " + req.getHeader(headerName) );
        }
        
        System.out.println("\n\nTESTE\n\n");
        
        String html = "<!DOCTYPE html>";                
        html += "<html>";
        html += "<head>";
        html += "<title>Servlet Ciclo</title>";
        html += "</head>";
        html += "<body>";
        html += "<h1>Servlet Ciclo</h1>";
        html += "</body>";
        html += "</html>";
        
        // MIME https://www.w3schools.com/tools/tool_mime_types.php
        resp.setContentType("text/html");
//        resp.setContentType("text/plain");
        
        PrintWriter pw = resp.getWriter();
        pw.print(html);
        pw.close();
        
    }    
    
    @Override
    public void destroy() {
        /*
        Chamado no encerramento do serviço de recebimentos de requisições/chamadas (verbos) HTTP pelo servidor, utilizado para a liberação de recursos.
        */
        super.destroy();
    }
    
    

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Ciclo</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Ciclo at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

    

    
}
