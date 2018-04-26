package com.example.sylviane.sia.activities.ListaAtividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.activities.AssistidosDetail.AssistidosDetailActivity;
import com.example.sylviane.sia.activities.AtividadesDetail.AtividadesDetailActivity;
import com.example.sylviane.sia.activities.Entity.AssistidosEntity;
import com.example.sylviane.sia.activities.Entity.AtividadesEntity;
import com.example.sylviane.sia.activities.ListaAssistidos.AssistidosActivity;
import com.example.sylviane.sia.activities.ListaAssistidos.AssistidosAdapter;
import com.example.sylviane.sia.activities.ListaAssistidos.AssistidosPresenter;
import com.example.sylviane.sia.activities.ListaAssistidos.OnRecyclerViewSelectedAssistidos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesActivity extends AppCompatActivity implements AtividadesView{

    @BindView(R.id.rvAtividades);
    RecyclerView rvAtividades;

    AtividadesPresenter atividadesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.listaAtividades);

        ButterKnife.bind(this);
        atividadesPresenter = new AtividadesPresenter(this);
        atividadesPresenter.updateList();
    }

    public void updateListAtividades(List<AtividadesEntity> atividadesList) {
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
}
