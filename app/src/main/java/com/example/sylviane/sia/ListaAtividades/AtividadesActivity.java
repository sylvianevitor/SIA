package com.example.sylviane.sia.ListaAtividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    int tipo_atividade;
    int id_tema;
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades);

        ButterKnife.bind(this);
        atividadesPresenter = new AtividadesPresenter(this);

        Intent intent = getIntent();
        tipo_atividade = intent.getIntExtra("tipo_atividade",-1);

        id_tema = intent.getIntExtra("id_tema",-1);
        Log.d("tipo da atividade", Integer.toString(tipo_atividade));
        Log.d("id tema", Integer.toString(id_tema));
        atividadesPresenter = new AtividadesPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Atividade atividadeDefault = new Atividade();
        List<Atividade> atividadeList = atividadeDAO.getAtividadeByTema(id_tema, tipo_atividade);
        atividadesPresenter.updateList(atividadeList);

        //atividadesPresenter.updateList(atividadeList);
        updateListAtividades(atividadeList);
    }

    public void updateListAtividades(final List<Atividade> atividadesList) {
        //seta o adapter

        Log.d("LISTA ATIVIDADE UPDATE", String.valueOf(atividadesList));
        for (int i = 0; i < atividadesList.size(); i++){
            Log.d("MARI", atividadesList.get(i).getNome());
        }

        AtividadesAdapter atividadesAdapter = new AtividadesAdapter(atividadesList, this);

        //Log.d("ATIVIDADES ADAPTER", String.valueOf(atividadesAdapter));

        atividadesAdapter.setOnRecyclerViewSelectedAtividades(new OnRecyclerViewSelectedAtividades() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AtividadesActivity.this,
                                AtividadesDetailActivity.class);
                intent.putExtra("atividade_id", atividadesList.get(position).getId());
                startActivity(intent);
                finish();
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
}
