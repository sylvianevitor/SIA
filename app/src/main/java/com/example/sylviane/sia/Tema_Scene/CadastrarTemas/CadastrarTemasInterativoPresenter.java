package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

import com.example.sylviane.sia.Main_Scene.MainView;

/**
 * Created by mariana on 02/05/18.
 */

public class CadastrarTemasInterativoPresenter {

    CadastrarTemasInterativosView cadastrarTemasInterativosView = null;

    public CadastrarTemasInterativoPresenter(CadastrarTemasInterativosView cadastrarTemasInterativosView){
        this.cadastrarTemasInterativosView = cadastrarTemasInterativosView;
    }


    public void salvarTema() {
        cadastrarTemasInterativosView.salvar();
    }
}
