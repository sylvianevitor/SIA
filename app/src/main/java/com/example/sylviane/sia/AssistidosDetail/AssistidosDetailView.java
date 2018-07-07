package com.example.sylviane.sia.AssistidosDetail;

import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Execucao;

import java.util.Date;
import java.util.List;

/**
 * Created by Natasha on 22/05/2018.
 */

public interface AssistidosDetailView {
    void showDetails(Assistido assistido);

    void showError();

    String calculaIdade(Date dataNasc);

    void updateListAtividades(List<Execucao> atividadesExecutadasListEntity);
}
