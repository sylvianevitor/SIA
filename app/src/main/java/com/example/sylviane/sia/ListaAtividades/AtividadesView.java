package com.example.sylviane.sia.ListaAtividades;

import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.Entity.AtividadesEntity;

import java.util.List;

/**
 * Created by Natasha on 25/04/2018.
 */

public interface AtividadesView {
    void updateListAtividades(List<Atividade> atividadesListEntity);
}
