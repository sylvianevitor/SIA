package com.example.sylviane.sia.SelecionarAtividade_Scene;

import com.example.sylviane.sia.Main_Scene.MainView;

/**
 * Created by mariana on 24/04/18.
 */

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
