package com.example.sylviane.sia.Main_Scene;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;

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
            Tema temaDefault = new Tema();
            AtividadeDAO atividadeDAO = new AtividadeDAO(contexto);
            TemaDAO temaDAO = new TemaDAO(contexto);
            if (temaDAO.getTemas().size() == 0){
                Log.d("Criando", "tema padrao");

                temaDefault.setTema("Atividades Padrão");
                temaDAO.insert(temaDefault);
            }
            if (atividadeDAO.getAtividadeId(1) == null) {

                Atividade atividadeDefault = new Atividade();
                atividadeDefault.setNome("Misturando Cores");
                atividadeDefault.setDescricao("Aprender sobre cores");
                atividadeDefault.setDificuldade(1);
                atividadeDefault.setObjetivo("Aprender sobre cores");
                atividadeDefault.setDt_cadastro("11/04/1997");
                atividadeDefault.setId_tema(temaDefault.getId());
                Log.d("Tema default", Integer.toString(atividadeDefault.getId_tema()));
                atividadeDAO.insert(atividadeDefault);
            }
    }
    public void popularAssistidos(Context contexto){
        AssistidoDAO assistidoDAO = new AssistidoDAO(contexto);
       // if (assistidoDAO.getAssistidoId(1) ==null){
            assistidoDAO.popularAssistidos();
       // }
    }

}