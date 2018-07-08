package com.example.sylviane.sia.Relatorios;

public class RelatorioAssistidosPresenter {
    RelatorioAssistidosView relatorioAssistidosView = null;

    public RelatorioAssistidosPresenter(RelatorioAssistidosView relatorioAssistidosView){
        this.relatorioAssistidosView = relatorioAssistidosView;
    }

    public void voltar_tela_inicial(){
        relatorioAssistidosView.sair();
    }
}
