package model;

import framework.util.Hash;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

// Bean -- Objeto Persistente.
public class Usuario {
    
    private int id;
    private String nome;
    private String senha;
    private int tipoUsuarioId;
    
    private String nascimento;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String uf;
    private String cep;
    
    private Integer convenioId;
   
    private Integer especialidadeId;
//    private int especialidadeId;
    
    private String expedienteInicio;
    private String expedienteFim;


    public Usuario(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if( id < 0 ) {
            throw new IllegalArgumentException("id não pode ser < 0");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException,  UnsupportedEncodingException {
        
        if( senha == null ) {
            throw new IllegalArgumentException("senha não pode ser null");
        }
        
        String aux = Integer.toString( getId() ) + senha;
        String hash = Hash.stringToHash( aux, "SHA-256" );
        this.senha = hash;
    }
    
    public void setSenhaHash(String senha) {
        
        if( senha == null ) {
            throw new IllegalArgumentException("senha não pode ser null");
        }
        this.senha = senha;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        if( tipoUsuarioId < 0 ) {
            throw new IllegalArgumentException("tipoUsuarioId não pode ser < 0");
        }
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getConvenioId() {
        return convenioId;
    }

    public void setConvenioId(Integer convenioId) {
        this.convenioId = convenioId;
    }
    
    public Integer getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Integer especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public String getExpedienteInicio() {
        return expedienteInicio;
    }

    public void setExpedienteInicio(String expedienteInicio) {
        this.expedienteInicio = expedienteInicio;
    }

    public String getExpedienteFim() {
        return expedienteFim;
    }

    public void setExpedienteFim(String expedienteFim) {
        this.expedienteFim = expedienteFim;
    }

    
    @Override
    public String toString() {
        return "(" + getId() + ", " + getNome() + ", " + getSenha() + ")";
    }
    
}