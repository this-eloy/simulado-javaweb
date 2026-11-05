package model;

public class TipoUsuario {
    
    private int id;
    private String moduloAdministrativo;
    private String moduloAgendamento;
    private String moduloAtendimento;

    public TipoUsuario(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if( id < 0 ) {
            throw new IllegalArgumentException("id não pode ser < 0");
        }
        this.id = id;
    }

    public String getModuloAdministrativo() {
        return moduloAdministrativo;
    }

    public void setModuloAdministrativo(String moduloAdministrativo) {
        
        if( moduloAdministrativo == null ) {
            throw new IllegalArgumentException("moduloAdministrativo não pode ser null");
        }
        
        this.moduloAdministrativo = moduloAdministrativo;
        
    }

    public String getModuloAgendamento() {
        return moduloAgendamento;
    }

    public void setModuloAgendamento(String moduloAgendamento) {
        
        if( moduloAgendamento == null ) {
            throw new IllegalArgumentException("moduloAgendamento não pode ser null");
        }
        
        this.moduloAgendamento = moduloAgendamento;
        
    }

    public String getModuloAtendimento() {
        return moduloAtendimento;
    }

    public void setModuloAtendimento(String moduloAtendimento) {
        
        if( moduloAtendimento == null ) {
            throw new IllegalArgumentException("moduloAtendimento não pode ser null");
        }
        
        this.moduloAtendimento = moduloAtendimento;
        
    }

}