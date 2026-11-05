package model;

public class Convenio {
    
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valor;

    public Convenio(int id) {
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
        if( nome == null ) {
            throw new IllegalArgumentException("nome não pode null");
        }
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if( valor < 0.0 ) {
            throw new IllegalArgumentException("valor não pode ser < 0.0");
        }
        this.valor = valor;
    }

}