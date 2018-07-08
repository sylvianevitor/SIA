package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.content.Intent;

import com.example.sylviane.sia.persist.model.Atividade;

public interface CriarAtividadePassivaView {
    interface View {
        void abreActivity(Intent intent, Integer codigo);
        void carregaVideo(String caminhoArquivo);
        void abrirMainActivity(boolean ok);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void selecionaVideo();
        Atividade getAtividade(int id_atividade);
        void cadastrar(String video);
    }
}
