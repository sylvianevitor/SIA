package com.example.sylviane.sia.SelecionarAtividade_Scene;

public class SelecionarAtividadesPresenter {

    SelecionarAtividadesView selecionarAtividadesView = null;

    public SelecionarAtividadesPresenter(SelecionarAtividadesView selecionarAtividadesView){
        this.selecionarAtividadesView = selecionarAtividadesView;
    }

    public void selecionarAtividadePassiva() {
        selecionarAtividadesView.selecionarPassiva();
    }

    public void selecionarAtividadeInterativa() {
        selecionarAtividadesView.selecionarInterativa();
    }
}
