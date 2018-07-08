package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

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
        boolean comparaTema(String nome);
    }
}
