package com.example.sylviane.sia.SelecaoAssistidos;

import com.example.sylviane.sia.Entity.AssistidosEntity;
import com.example.sylviane.sia.Entity.AssistidosListEntity;
import com.example.sylviane.sia.ListaAssistidos.AssistidosView;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylviane on 14/05/18.
 */

public class SelecaoAssistidosPresenter {

    private SelecaoAssistidosView assistidosView;
    private List<Assistido> assistidosList = new ArrayList<>();

    SelecaoAssistidosPresenter(SelecaoAssistidosView assistidosView){
        this.assistidosView = assistidosView;
    }

    //pega informações do banco
    public void updateList(List<Assistido> listassistidos) {

    }
}
