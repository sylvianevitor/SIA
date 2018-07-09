package com.example.sylviane.sia.Atividade.Cores_Scene;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.model.Execucao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateExec = format1.format(cal.getTime());
        execucao.setData(dateExec);
        int seconds= (int) ((tempo/1000)%60);
        execucao.setTempo((float) seconds);
        execucao.setId_assistido(assistidos);
        ExecucaoDAO execucaoDAO = new ExecucaoDAO(contexto);
        execucaoDAO.insert(execucao);
        return execucao.getId();
    }


}
