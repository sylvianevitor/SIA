package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.content.Intent;

import com.example.sylviane.sia.persist.model.Atividade;


interface CriarTemplate1View {

    interface View {
        void abreActivity(Intent intent, Integer codigo, int id);
        void carregaImagem(String caminhoArquivo);
        void carregaAudio(String path);
        void abrirMainActivity(boolean b, boolean ok2, boolean ok1);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void selecionaImagem(int id);
        void selecionaAudio(int id);
        Atividade getAtividade(int id_atividade);
        void cadastrar(String audio1, String audio2, String audio3, String pathAudio1, String pathAudio2, String pathAudio3);
    }
}
