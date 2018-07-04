package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import com.example.sylviane.sia.persist.model.Tema;

import java.util.ArrayList;

/**
 * Created by mariana on 01/05/18.
 */

public interface Contract {

    interface View {
        void preenchimento(String nome, String objetivo, String descricao);
        void preencheSpinnerTemas(ArrayList<Tema> temas);
        void abrirAtividade(int id);
    }

    interface Presenter{
        void cadastro(String nome, String objetivo, String descricao, Integer dificuldade, Tema tema);
        void getTemas();
        void getAtividade(int id_atividade);
    }
}


