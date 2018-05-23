package com.example.sylviane.sia.Relatorios;

public class RelatoriosPresenter {
    RelatoriosView relatoriosView = null;

    public RelatoriosPresenter(RelatoriosView relatoriosView){
        this.relatoriosView = relatoriosView;
    }

    public void voltar_tela_inicial(){
        relatoriosView.sair();
    }
}
