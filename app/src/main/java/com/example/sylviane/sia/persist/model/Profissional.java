package com.example.sylviane.sia.persist.model;

public class Profissional {

    private int id;
    private String nome_completo;
    private String apelido;
    private int id_formacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getId_formacao() {
        return id_formacao;
    }

    public void setId_formacao(int id_formacao) {
        this.id_formacao = id_formacao;
    }
}
