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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TipoUsuario;

public class FiltroAdm implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        TipoUsuario tp = (TipoUsuario) sessao.getAttribute("tipo_usuario_sessao");
        
        if( tp.getModuloAdministrativo().equals("S") ) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(403);
        }
        
    }    

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    
    
}
