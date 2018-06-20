package com.example.sylviane.sia.Tema_Scene.Tema_Detail;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sylviane.sia.ListaAtividades.AtividadesActivity;
import com.example.sylviane.sia.ListaAtividades.AtividadesAdapter;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mariana on 19/06/18.
 */

public class TemaDetailActivity extends  AppCompatActivity implements TemaDetailView{

    AtividadeDAO atividadeDAO;

    @BindView(R.id.rvAtividades) RecyclerView rvAtividades;
    TemaDetailPresenter temaDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistidos_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        int temaId = intent.getIntExtra("tema_id", -1);


        temaDetailPresenter = new TemaDetailPresenter(this, this);
        temaDetailPresenter.getTemaDetails(temaId);
    }

    @Override
    public void showDetails(int temaId) {
        //Exibir lista de atividades.
//        List<Atividade> atividadeList = atividadeDAO.getAtividade();
//        List<Atividade> atividadeTema = null;
//
//        for (int i = 0; i < atividadeList.size(); i++){
//            if(atividadeList.get(i).getId_tema() == temaId){
//                atividadeTema.add(i, atividadeList.get(i));
//            }
//        }

        Intent openListaAtividadesActivity = new Intent(TemaDetailActivity.this, AtividadesActivity.class);
        openListaAtividadesActivity.putExtra("tema_id", temaId);
        startActivity(openListaAtividadesActivity);

    }

    @Override
    public void showError() {
        Toast.makeText(this,"Erro ao pegar infos do banco",Toast.LENGTH_LONG);
    }
}