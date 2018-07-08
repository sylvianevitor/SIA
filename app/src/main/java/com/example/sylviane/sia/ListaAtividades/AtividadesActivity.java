package com.example.sylviane.sia.ListaAtividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.AtividadesDetail.AtividadesDetailActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        atividadesPresenter = new AtividadesPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Atividade> atividadeList = atividadeDAO.getAtividadeByTema(id_tema, tipo_atividade);
        atividadesPresenter.updateList(atividadeList);
        updateListAtividades(atividadeList);
    }

    public void updateListAtividades(final List<Atividade> atividadesList) {
        //seta o adapter
        AtividadesAdapter atividadesAdapter = new AtividadesAdapter(atividadesList, this);
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
