package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import android.content.Context;

import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

public class TemaInterativoPresenter implements Contract.Presenter{

    private Contract.View temaInterativoView;
    private Context context;


    TemaInterativoPresenter(Contract.View temaInterativoView, Context context) {
        this.temaInterativoView = temaInterativoView;
        this.context = context;
    }

    public void getTema() {
        TemaDAO temaDAO = new TemaDAO(context);
        List<Tema> temaList = temaDAO.getTemas(); //buscar todos os temas no BD
        temaInterativoView.updateList(temaList);
    }

    public void cadastrarTema() {
        temaInterativoView.cadastrar();
    }
}
