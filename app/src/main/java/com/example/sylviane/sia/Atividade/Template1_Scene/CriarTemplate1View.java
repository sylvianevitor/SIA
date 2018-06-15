package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.content.Intent;

/**
 * Created by mariana on 01/05/18.
 */

interface CriarTemplate1View {

    interface View {
        void abreActivity(Intent intent, Integer codigo, int id);
        void carregaImagem(String caminhoArquivo);
        void carregaAudio(String path);
        void cadastrar();
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void selecionaImagem(int id);
        void selecionaAudio(int id);

    }
}
