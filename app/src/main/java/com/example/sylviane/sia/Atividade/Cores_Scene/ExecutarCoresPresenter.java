package com.example.sylviane.sia.Atividade.Cores_Scene;

import android.content.Context;
import android.content.Intent;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.Relatorios.RelatoriosActivity;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.model.Execucao;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sylviane on 12/06/18.
 */

public class ExecutarCoresPresenter {
    ExecutarCoresView executarCoresView;
    Context contexto;
    Execucao execucao = new Execucao();

    public ExecutarCoresPresenter(ExecutarCoresView executarCoresView, Context contexto){
        this.executarCoresView = executarCoresView;
        this.contexto = contexto;
    }

    public void sair(Context contexto, ArrayList<Integer> assistidos, long tempo){
        Intent telainicial = new Intent(contexto, MainActivity.class);
        contexto.startActivity(telainicial);
        gravar_execucao(assistidos, tempo, contexto);

    }
    public int gravar_execucao(ArrayList<Integer> assistidos, long tempo, Context contexto){
        execucao.setId_atividade(1);
        Calendar cal = Calendar.getInstance();
        String dateExec = String.valueOf(cal);
        execucao.setData(dateExec);
        execucao.setTempo((float) tempo);
        execucao.setId_assistido(assistidos);
        ExecucaoDAO execucaoDAO = new ExecucaoDAO(contexto);
        execucaoDAO.insert(execucao);
        return execucao.getId();
    }


}
