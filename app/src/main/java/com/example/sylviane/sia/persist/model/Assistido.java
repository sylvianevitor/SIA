package com.example.sylviane.sia.persist.model;

public class Assistido {

    private int id;
    private String nome_completo;
    private String apelido;
    private String dt_nasc;
    private String responsavel;
    private String telefone;
    private String informacoes;
    private String medicamentos;
    private String imagem;
    private int idade;
    private boolean selected;

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }

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

    public String getMedicamentos() { return medicamentos; }

    public void setMedicamentos(String medicamentos) { this.medicamentos = medicamentos; }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public boolean isSelected() {
        return selected;
    }
}
