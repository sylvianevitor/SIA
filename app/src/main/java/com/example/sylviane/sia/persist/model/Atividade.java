package com.example.sylviane.sia.persist.model;

import android.content.Context;

public class Atividade {

    private int id;
    private String nome;
    private String descricao;
    private int dificuldade;
    private int id_proprietario;
    private String dt_cadastro;
    private int nr_execucoes;
    private int id_tema;
    private int tipo_atividade;

    public static final int TIPO_PASSIVA = 0;
    public static final int TIPO_ATIVA = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getId_proprietario() {
        return id_proprietario;
    }

    public void setId_proprietario(int id_proprietario) {
        this.id_proprietario = id_proprietario;
    }

    public String getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(String dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public int getNr_execucoes() {
        return nr_execucoes;
    }

    public void setNr_execucoes(int nr_execucoes) {
        this.nr_execucoes = nr_execucoes;
    }

    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }

    public int getTipo_atividade() {
        return tipo_atividade;
    }

    public void setTipo_atividade(int tipo_atividade) {
        this.tipo_atividade = tipo_atividade;
    }
}
