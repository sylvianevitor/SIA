package com.example.sylviane.sia.AtividadesDetail;

/**
 * Created by sylviane on 15/06/18.
 */

public class AtividadesDetailPresenter {

    AtividadesDetailView descricaoAtividadeView = null;

    public AtividadesDetailPresenter(AtividadesDetailView descricaoAtividadeView){
        this.descricaoAtividadeView = descricaoAtividadeView;
    }

    public void iniciar() {
        descricaoAtividadeView.abreDescricao();
    }

}
