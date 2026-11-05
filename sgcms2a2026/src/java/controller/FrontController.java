package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import framework.log.ExceptionLogTrack;
import jakarta.servlet.http.Cookie;

import model.*;

public class FrontController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String task = req.getParameter("task");
        
        try {
        
            switch (task) {
                
                case "login": doDefault(req, resp); break;
                
                case "logout": doGetLogout(req, resp); break;

                case "tipousuario": doGetTipoUsuario(req, resp); break;
                
                case "usuario": doGetUsuario(req, resp); break;
                
                case "convenio": doGetConvenio(req, resp); break;
                
                case "especialidade": doGetEspecialidade(req, resp); break;

                case null:
                default:
                    doDefault(req, resp);

            }
            
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException( ex );
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String task = req.getParameter("task");
        
        try {
        
            switch (task) {

                case "login": doPostLogin(req, resp); break;
                
                case "tipousuario": doPostTipoUsuario(req, resp); break;
                
                case "usuario": doPostUsuario(req, resp); break;
                
                case "convenio": doPostConvenio(req, resp); break;
                
                case "especialidade": doPostEspecialidade(req, resp); break;

                case null:
                default:
                    doDefault(req, resp);

            }
            
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException( ex );
        }
        
    }


    
    
    private void doDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        
    }
    
    
    private void doGetLogout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        HttpSession sessao = req.getSession(false);
        if( sessao != null ) {
            sessao.removeAttribute("tipo_usuario_sessao");
            sessao.removeAttribute("usuario_sessao");
            sessao.invalidate();
        }
                
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        
        
    }
    
    private void doGetTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            TipoUsuario usTp = new TipoUsuario(id); // bean
            
            TipoUsuarioDAO dao = new TipoUsuarioDAO(); // dao
            
            dao.delete(usTp);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
        
        
    }
    
    private void doGetUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            Usuario us = new Usuario(id); // bean
            
            UsuarioDAO dao = new UsuarioDAO(); // dao
            
            dao.delete(us);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
        
        
    }
    
    private void doGetConvenio(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            Convenio cv = new Convenio(id); // bean
            
            ConvenioDAO dao = new ConvenioDAO(); // dao
            
            dao.delete(cv);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/convenio.jsp").forward(req, resp);
        
        
    }
    
    private void doGetEspecialidade(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action");
        
        if( ( action != null ) && ( action.equals("delete") ) ) {
            
            int id = Integer.parseInt( req.getParameter("id") );
            
            Especialidade esp = new Especialidade(id); // bean
            
            EspecialidadeDAO dao = new EspecialidadeDAO(); // dao
            
            dao.delete(esp);
            
        }
        
        req.getRequestDispatcher("/home/app/adm/especialidades.jsp").forward(req, resp);
        
        
    }
    
    
    
    private void doPostLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        int id = Integer.parseInt( req.getParameter("id") );
        String senha = req.getParameter("senha");
        
        UsuarioDAO dao = new UsuarioDAO(); // dao
        
        Usuario us = dao.getUnique(id);
        
        Usuario usTry = new Usuario(id);
        usTry.setSenha(senha);
        
        Cookie cookieId = new Cookie( "id", String.valueOf(id) );
        cookieId.setMaxAge( 60 * 2 ); // segundos
        resp.addCookie(cookieId);
        
        if( ( us != null ) && ( us.getSenha().equals( usTry.getSenha() ) )  ) {
            
            TipoUsuario usTp = new TipoUsuarioDAO().getUnique( us.getTipoUsuarioId() );
            
            HttpSession sessao = req.getSession(false);
            if( sessao != null ) {
                sessao.invalidate();
            }
            
            sessao = req.getSession(true);
            sessao.setMaxInactiveInterval( 60 * 10 ); // segundos
            sessao.setAttribute("tipo_usuario_sessao", usTp);
            sessao.setAttribute("usuario_sessao", us);
            
            req.getRequestDispatcher("/home/app/menu.jsp").forward(req, resp);            
            
        } else {
            req.setAttribute("msg", "id e/ou senha incorreto(s)!");
            req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        }
        
    }
    
    private void doPostTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        
        String moduloAdministrativo = req.getParameter("modulo_administrativo");
        if( moduloAdministrativo == null ) moduloAdministrativo = "N";
        
        String moduloAgendamento = req.getParameter("modulo_agendamento");
        if( moduloAgendamento == null ) moduloAgendamento = "N";
        
        String moduloAtendimento = req.getParameter("modulo_atendimento");
        if( moduloAtendimento == null ) moduloAtendimento = "N";
        
        TipoUsuario usTp = new TipoUsuario(id); // bean
        usTp.setModuloAdministrativo(moduloAdministrativo);
        usTp.setModuloAtendimento(moduloAtendimento);
        usTp.setModuloAgendamento(moduloAgendamento);
        
        TipoUsuarioDAO dao = new TipoUsuarioDAO(); // dao
        
        if( action.equals("new") ) dao.insert( usTp );
        
        if( action.equals("update") ) dao.update( usTp );
        
        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
        
    }
    
    private void doPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        String nome = req.getParameter("nome"); // sem o tratamento do vazio como null
        String senha = req.getParameter("senha");
        int tipoUsuarioId = Integer.parseInt( req.getParameter("tipo_usuario_id") );
        
        Usuario us = new Usuario(id); // bean
        us.setNome(nome);
        
        if( senha.length() > 20 ) {
            us.setSenhaHash(senha);
        } else {
            us.setSenha(senha);
        }
        
        us.setTipoUsuarioId(tipoUsuarioId);
        
        String nascimento = req.getParameter("nascimento");
        if( nascimento.isBlank() ) {
            nascimento = null;
        }
        
        String logradouro = req.getParameter("logradouro");
        if( logradouro.isBlank() ) {
            logradouro = null;
        }
        
        String numero = req.getParameter("numero");
        if( numero.isBlank() ) {
            numero = null;
        }
        
        String bairro = req.getParameter("bairro");
        if( bairro.isBlank() ) {
            bairro = null;
        }
        
        String cidade = req.getParameter("cidade");
        if( cidade.isBlank() ) {
            cidade = null;
        }
        
        String estado = req.getParameter("estado");
        if( estado.isBlank() ) {
            estado = null;
        }
        
        String uf = req.getParameter("uf");
        if( uf.isBlank() ) {
            uf = null;
        }
        
        String cep = req.getParameter("cep");
        if( cep.isBlank() ) {
            cep = null;
        }
        
        String convenioId = req.getParameter("convenio_id");
        if( convenioId.isBlank() ) {
            convenioId = null;
        }
        
        String especialidadeId = req.getParameter("especialidade_id");
        if( especialidadeId.isBlank() ) {
            especialidadeId = null;
        }
        
        String expedienteInicio = req.getParameter("expediente_inicio");
        if( expedienteInicio.isBlank() ) {
            expedienteInicio = null;
        }
        
        String expedienteFim = req.getParameter("expediente_fim");
        if( expedienteFim.isBlank() ) {
            expedienteFim = null;
        }
        
        
        us.setNascimento(nascimento);
        us.setLogradouro(logradouro);
        us.setNumero(numero);
        us.setBairro(bairro);
        us.setCidade(cidade);
        us.setEstado(estado);
        us.setUf(uf);
        us.setCep(cep);
        
        if( convenioId != null ) {
            us.setConvenioId( Integer.parseInt( convenioId ) );
        } else {
            us.setConvenioId(null);
        }
        
        if( especialidadeId != null ) {
            us.setEspecialidadeId( Integer.parseInt( especialidadeId ) );
        } else {
            us.setEspecialidadeId(null);
        }
        
        us.setExpedienteInicio(expedienteInicio);
        us.setExpedienteFim(expedienteFim);
        
        UsuarioDAO dao = new UsuarioDAO(); // dao
        
        if( action.equals("new") ) dao.insert( us );
        
        if( action.equals("update") ) dao.update( us );
        
        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
        
    }
    
    private void doPostConvenio(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        
        String nome = req.getParameter("nome");
        if( nome.isBlank() ) {
            nome = null;
        }
        
        String cnpj = req.getParameter("cnpj");
        if( cnpj.isBlank() ) {
            cnpj = null;
        }
        
        String telefone = req.getParameter("telefone");
        if( telefone.isBlank() ) {
            telefone = null;
        }
        
        double valor = Double.parseDouble( req.getParameter("valor") );
        
        
        Convenio cv  = new Convenio(id);
        
        cv.setNome(nome);
        cv.setCnpj(cnpj);
        cv.setTelefone(telefone);
        cv.setValor(valor);
        
        ConvenioDAO dao = new ConvenioDAO();
        
        if( action.equals("new") ) dao.insert( cv );
        
        if( action.equals("update") ) dao.update( cv );
        
        req.getRequestDispatcher("/home/app/adm/convenio.jsp").forward(req, resp);
        
    }
    
    private void doPostEspecialidade(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        String action = req.getParameter("action"); // new || update
        
        int id = Integer.parseInt( req.getParameter("id") );
        
        String nome = req.getParameter("nome");
        if( nome.isBlank() ) {
            nome = null;
        }
        
        String descricao = req.getParameter("descricao");
        if( descricao.isBlank() ) {
            descricao = null;
        }      
        
        Especialidade esp = new Especialidade(id); 
        
        esp.setNome(nome);
        esp.setDescricao(descricao);
        
        EspecialidadeDAO dao = new EspecialidadeDAO();       
        
        if( action.equals("new") ) dao.insert( esp );
        
        if( action.equals("update") ) dao.update( esp );
        
        req.getRequestDispatcher("/home/app/adm/especialidade.jsp").forward(req, resp);
        
    }
    

}