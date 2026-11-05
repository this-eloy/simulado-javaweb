package aulas.rotalogica.servlet.status;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Status", urlPatterns = {"/aulas/rotalogica/servlet/status"})
public class Status extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // https://www.w3schools.com/tools/tool_http_status.php
        
//        int i = 1 / 0; // status code 5xx        
//        http://localhost:8080/sgcms2a2026/aulas/rotalogica/servlet/status_v0 // status code 4xx

//        PrintWriter pw = resp.getWriter();
//        pw.write("https://www.w3schools.com/tools/tool_http_status.php");
//        pw.close(); // status code 2xx

        resp.sendRedirect("https://www.w3schools.com/tools/tool_http_status.php"); // status code 3xx
        
    }

}
