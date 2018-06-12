package com.example.sylviane.sia.Atividade.Cores_Scene;

import android.content.Context;
import android.content.Intent;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.Relatorios.RelatoriosActivity;

/**
 * Created by sylviane on 12/06/18.
 */

public class ExecutarCoresPresenter {
    ExecutarCoresView executarCoresView;
    Context contexto;

    public ExecutarCoresPresenter(ExecutarCoresView executarCoresView, Context contexto){
        this.executarCoresView = executarCoresView;
        this.contexto = contexto;
    }

    public void sair(int pontuacao, Context contexto){
        Intent telainicial = new Intent(contexto, MainActivity.class);
        //salvar execucao no banco
        contexto.startActivity(telainicial);
    }


}
