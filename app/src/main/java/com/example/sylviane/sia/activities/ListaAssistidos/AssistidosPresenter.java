package com.example.sylviane.sia.activities.ListaAssistidos;

import com.example.sylviane.sia.activities.Entity.AssistidosEntity;
import com.example.sylviane.sia.activities.Entity.AssistidosListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AssistidosPresenter {

    private AssistidosView assistidosView;
    private List<AssistidosEntity> assistidosList = new ArrayList<>();
    AssistidosListEntity assistidosListEntity;

    AssistidosPresenter(AssistidosView assistidosView){
        this.assistidosView = assistidosView;
    }

    //pega informações do banco
    public void updateList() {
    }
}
