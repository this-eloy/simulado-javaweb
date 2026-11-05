package model;

public class Especialidade {
    private int id;
    private String nome;
    private String descricao;

    public Especialidade(int id) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if( nome == null ) {
            throw new IllegalArgumentException("nome não pode null");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
