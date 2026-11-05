
package aulas.rotalogica.servlet.metodos;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
Implementação das requisões (métodos/verbos) HTTP via Servlet.

GET
POST
PUT
HEAD
DELETE
PATCH
OPTIONS
CONNECT
TRACE

https://www.w3schools.com/tags/ref_httpmethods.asp
*/
@WebServlet(name = "Metodos", urlPatterns = {"/aulas/rotalogica/servlet/metodos"})
public class Metodos extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("GET é utilizado para a obtenção de recursos do servidor. As requisções podem ser cached, possuem limite de tamanho e a query string (pares parâmetro=valor) é enviada junto com a URL.");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("POST é utilizado para enviar dados para o servidor para criação ou atualização de um recurso. As requisiões não pode ser cached, não possui limite de tamanho e os dados (pares parâmetro=valor) são enviados no corpo da requisição.");
        pw.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("PUT é utilizado para enviar dados para o servidor para criação ou atualização de um recurso (sem duplicação por rechamada).");
        pw.close();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("HEAD é similar ao GET, com a diferença que a resposta vem sem o copo (somente cabeçalhos).");
        pw.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("DELETE é utilizado para deletar um determinado recurso no servidor.");
        pw.close();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("OPTIONS é utiliado para apresentar os métodos HTTP disponíveis no servidor.");
        pw.close();
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.write("TRACE é utilizado para depuração apresentando o caminho de um determiando rercuso.");
        pw.close();
    }
    
    /*
    Servlet não possui suporte nativo para:
    
    PATCH: Aplica atualizaçẽos parciais a rercusos no servidor.
    CONNECT: Utilizao para iniciar comunicações de "mão dupla" com o recurso requisitado no servidor.
    */
    
}
