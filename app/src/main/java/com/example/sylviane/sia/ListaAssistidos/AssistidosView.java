package com.example.sylviane.sia.ListaAssistidos;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.Entity.AssistidosEntity;


import java.util.List;

/**
 * Created by Natasha on 25/04/2018.
 */

public interface AssistidosView {
    void updateList(List<Assistido> assistidosListEntities);
}
