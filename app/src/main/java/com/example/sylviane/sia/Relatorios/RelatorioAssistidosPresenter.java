package com.example.sylviane.sia.Relatorios;

/**
 * Created by Rafael Saito on 21/05/2018.
 */

public class RelatorioAssistidosPresenter {
    RelatorioAssistidosView relatorioAssistidosView = null;

    public RelatorioAssistidosPresenter(RelatorioAssistidosView relatorioAssistidosView){
        this.relatorioAssistidosView = relatorioAssistidosView;
    }

    public void voltar_tela_inicial(){
        relatorioAssistidosView.sair();
    }
}
