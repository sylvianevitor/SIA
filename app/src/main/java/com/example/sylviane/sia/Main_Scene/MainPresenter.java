package com.example.sylviane.sia.Main_Scene;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.ArrayList;


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
                Log.d("Criando", "Tema e Atividades Padrão");

                temaDefault.setTema("Atividades Padrão");
                temaDefault = temaDAO.insert(temaDefault);

                Atividade atividadeDefault = new Atividade();
                atividadeDefault.setNome("Misturando Cores");
                atividadeDefault.setDescricao("Aprender sobre cores");
                atividadeDefault.setDificuldade(1);
                atividadeDefault.setObjetivo("Aprender sobre cores");
                atividadeDefault.setDt_cadastro("11/04/1997");
                atividadeDefault.setId_tema(temaDefault.getId());
                atividadeDefault.setTipo_atividade(Atividade.TIPO_ATIVA);
                atividadeDAO.insert(atividadeDefault);
            }

    }
    public void popularAssistidos(Context contexto){
        AssistidoDAO assistidoDAO = new AssistidoDAO(contexto);

        ArrayList<Assistido> listAssistidos = assistidoDAO.getAssistidos();

        if(listAssistidos.size() <= 0)
            assistidoDAO.popularAssistidos();

    }

}