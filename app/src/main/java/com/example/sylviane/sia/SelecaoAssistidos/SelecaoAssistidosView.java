package com.example.sylviane.sia.SelecaoAssistidos;

import com.example.sylviane.sia.persist.model.Assistido;
import java.util.List;


public interface SelecaoAssistidosView {
    void updateList(List<Assistido> assistidosList);
    void iniciar();
}

