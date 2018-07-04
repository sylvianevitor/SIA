package com.example.sylviane.sia.Main_Scene;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

/**
 * Created by mariana on 24/04/18.
 */

public class MainPresenter extends AppCompatActivity {
    Context context;
    MainView mainView = null;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;
    }

    public void criarAtividade() {
        mainView.criar();
    }

    public void selecionarAtividade() {
        mainView.selecionar();
    }

    public void verAssistidos() {
        mainView.ver();
    }

    public void creatAtivDefault(Context contexto){

            AtividadeDAO atividadeDAO = new AtividadeDAO(contexto);
            Atividade atividadeDefault = new Atividade();

            atividadeDefault.setNome("Misturando Cores");
            atividadeDefault.setDescricao("Aprender sobre cores");
            atividadeDefault.setDificuldade(1);
            atividadeDefault.setObjetivo("Aprender sobre cores");
            atividadeDefault.setDt_cadastro("11/04/1997");
            atividadeDAO.insert(atividadeDefault);

    }

}