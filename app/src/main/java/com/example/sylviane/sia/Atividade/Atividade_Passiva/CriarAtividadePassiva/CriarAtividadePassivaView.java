package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.content.Intent;

/**
 * Created by Natasha on 26/06/2018.
 */

public interface CriarAtividadePassivaView {
    interface View {
        void abreActivity(Intent intent, Integer codigo);
        void carregaVideo(String caminhoArquivo);
        void abrirMainActivity(boolean ok);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void selecionaVideo();
        void cadastrar(String video);
    }
}
