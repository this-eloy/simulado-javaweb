package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FiltroApp implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        if( ( sessao != null ) &&
                ( sessao.getAttribute("tipo_usuario_sessao") != null ) && 
                ( sessao.getAttribute("usuario_sessao") != null ) ) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute("msg", "faça o login!");
            request.getRequestDispatcher("/home/login.jsp").forward(request, response);
        }
        
    }    

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    
}