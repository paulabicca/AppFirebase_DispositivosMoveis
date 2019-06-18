package com.example.conexaofirebase;

public class Anotacao {
    private String nome;
    private String id;

    public Anotacao(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public Anotacao() {

    }

    @Override
    public String toString() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() { return id;  }

    public void setId(String id) { this.id = id;  }
}
