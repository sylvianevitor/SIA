package com.example.sylviane.sia.AtividadesDetail;

public class AtividadesDetailPresenter {

    AtividadesDetailView descricaoAtividadeView = null;

    public AtividadesDetailPresenter(AtividadesDetailView descricaoAtividadeView){
        this.descricaoAtividadeView = descricaoAtividadeView;
    }

    public void iniciar() {
        descricaoAtividadeView.abreDescricao();
    }

}
