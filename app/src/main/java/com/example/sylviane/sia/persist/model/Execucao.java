package com.example.sylviane.sia.persist.model;

public class Execucao {
    private int id;
    private int id_atividade;
    private int id_assistido;
    private String data;
    private String hora;
    private Float perc_acertos;
    private Float tempo;
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public int getId_assistido() {
        return id_assistido;
    }

    public void setId_assistido(int id_assistido) {
        this.id_assistido = id_assistido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Float getPerc_acertos() {
        return perc_acertos;
    }

    public void setPerc_acertos(Float perc_acertos) {
        this.perc_acertos = perc_acertos;
    }

    public Float getTempo() {
        return tempo;
    }

    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
