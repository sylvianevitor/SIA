package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.content.Intent;

/**
 * Created by Natasha on 26/06/2018.
 */

public interface CriarAtividadePassivaView {
    interface View {
        void abreActivity(Intent intent, Integer codigo);
        void cadastrar();
        void carregaVideo(String caminhoArquivo);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void selecionaImagem(int id);
        void selecionaAudio(int id);

    }
}
