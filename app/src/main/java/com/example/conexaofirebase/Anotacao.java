package com.example.conexaofirebase;

public class Anotacao {
    private String id;
    private String nome;


    public Anotacao(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    @Override
    public String toString() {
        return this.nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
