package model;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        
//        Usuario usuario = new Usuario(13);
//        usuario.setNome("Usuário 13");
//        usuario.setSenha("1313");
//        
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
//        usuarioDAO.insert(usuario);

//        usuario.setNome("Usuário 13 13 13");
//        usuario.setSenha("3131");
//        usuarioDAO.update(usuario);
//        
//        usuarioDAO.delete(usuario);

//        ArrayList<Usuario> listaUsuarios = usuarioDAO.getAll();
//        System.out.println( listaUsuarios );
//        
//        usuario = usuarioDAO.getUnique(1951);
//        System.out.println( usuario );
//        
//        usuario = usuarioDAO.getUnique(13);
//        System.out.println( usuario );

        TipoUsuario tp = new TipoUsuario(13);
        tp.setModuloAdministrativo("N");
        tp.setModuloAgendamento("N");
        tp.setModuloAtendimento("N");
        
        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
        
//        tipoUsuarioDAO.insert(tp);
//        tipoUsuarioDAO.update(tp);
        tipoUsuarioDAO.delete(tp);
        
        System.out.println( tipoUsuarioDAO.getUnique(1951) );
        System.out.println( tipoUsuarioDAO.getUnique(12) );
        System.out.println( tipoUsuarioDAO.getAll() );

        
    }
}