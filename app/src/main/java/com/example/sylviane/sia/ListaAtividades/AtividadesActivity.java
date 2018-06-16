package com.example.sylviane.sia.ListaAtividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sylviane.sia.Atividade.Cores_Scene.ExecutarCoresActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.AtividadesDetail.AtividadesDetailActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesActivity extends AppCompatActivity implements AtividadesView{

    @BindView(R.id.rvAtividades) RecyclerView rvAtividades;

    AtividadesPresenter atividadesPresenter;
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades);

        ButterKnife.bind(this);
        atividadesPresenter = new AtividadesPresenter(this);

        Atividade atividadeDefault = new Atividade();

        creatAtivCores(atividadeDefault);
        List<Atividade> atividadeList = atividadeDAO.getAtividade();

        atividadesPresenter.updateList(atividadeList);
    }

    public void updateListAtividades(List<Atividade> atividadesList) {
        //seta o adapter
        AtividadesAdapter atividadesAdapter = new AtividadesAdapter(atividadesList, this);

        atividadesAdapter.setOnRecyclerViewSelectedAtividades(new OnRecyclerViewSelectedAtividades() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AtividadesActivity.this,
                                AtividadesDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(MoviesActivity.this, "Clique Longo", Toast.LENGTH_SHORT).show();
            }
        });

        rvAtividades.setAdapter(atividadesAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAtividades.setLayoutManager(layoutManager);
        rvAtividades.addItemDecoration(dividerItemDecoration);
    }

    public void creatAtivCores(Atividade atividade){
        atividade.setNome("Misturando Cores");
        atividade.setDescricao("Aprender sobre cores");
        atividade.setDificuldade(1);
        atividade.setObjetivo("Aprender sobre cores");
        atividade.setDt_cadastro("11/04/1997");
        // verificar se já existe
        if(atividadeDAO.getAtividadeId(1) == null)
            atividadeDAO.insert(atividade);
    }
}
