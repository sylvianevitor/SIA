package com.example.sylviane.sia.activities.ListaAtividades;

import com.example.sylviane.sia.activities.Entity.AtividadesEntity;
import com.example.sylviane.sia.activities.Entity.AtividadesListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesPresenter {

    private AtividadesView atividadesView;
    private List<AtividadesEntity> atividadesEntityList = new ArrayList<>();
    AtividadesListEntity atividadesListEntity;

    AtividadesPresenter(AtividadesView atividadesView){
        this.atividadesView = atividadesView;
    }

    //pega informações do banco
    public void updateList() {
    }
}
