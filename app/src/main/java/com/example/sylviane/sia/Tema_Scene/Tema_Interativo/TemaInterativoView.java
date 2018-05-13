package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import com.example.sylviane.sia.com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

/**
 * Created by mariana on 25/04/18.
 */

public interface TemaInterativoView {
    void updateList(List<Tema> socialList);

    void cadastrar();
}
