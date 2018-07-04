package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

/**
 * Created by mariana on 02/05/18.
 */

public interface Contract {

    interface View{
        void salvar();
        void ligarCamera();
        void camera();
        void abrirActivity(boolean ok);
    }

    interface Presenter{
        void salvarTema(String nome, String imagem);
        void ligarCamera();
    }
}
