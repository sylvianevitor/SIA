package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import com.example.sylviane.sia.persist.model.Tema;

import java.util.ArrayList;

public interface Contract {

    interface View {
        void preenchimento(String nome, String objetivo, String descricao);
        void preencheSpinnerTemas(ArrayList<Tema> temas);
        void abrirAtividade(int id);
    }

    interface Presenter{
        void cadastro(String nome, String objetivo, String descricao, Integer dificuldade, Tema tema, Integer tipo);
        void getTemas();
        void getAtividade(int id_atividade);
        boolean comparaNome(String nome);
    }
}


