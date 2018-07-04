package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import android.content.Intent;

import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

/**
 * Created by mariana on 25/04/18.
 */

public interface Contract {

    interface View{
        void updateList(List<Tema> temaList);
        void cadastrar();
        void voltar();
    }

    interface Presenter{
        void getTema();
        void cadastrarTema();
    }

}
