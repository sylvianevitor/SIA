package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

public interface Contract {

    interface View{
        void updateList(List<Tema> temaList);
        void cadastrar();
    }

    interface Presenter{
        void getTema();
        void cadastrarTema();
    }

}
