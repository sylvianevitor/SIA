package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

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
