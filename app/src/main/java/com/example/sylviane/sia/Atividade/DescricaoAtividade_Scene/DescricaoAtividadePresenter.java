package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Context;

import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

/**
 * Created by mariana on 01/05/18.
 */

public class DescricaoAtividadePresenter {

    DescricaoAtividadeView descricaoAtividadeView = null;

    public DescricaoAtividadePresenter(DescricaoAtividadeView descricaoAtividadeView){
        this.descricaoAtividadeView = descricaoAtividadeView;
    }

    public void cadastro() {
        descricaoAtividadeView.efetuaCadastro();
    }

}
