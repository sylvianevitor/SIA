package com.example.sylviane.sia.ListaAssistidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.sylviane.sia.R;
import com.example.sylviane.sia.AssistidosDetail.AssistidosDetailActivity;
import com.example.sylviane.sia.Entity.AssistidosEntity;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AssistidosActivity extends AppCompatActivity implements AssistidosView{

    @BindView(R.id.rvAssistidos) RecyclerView rvAssistidos;

    AssistidosPresenter assistidosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_assistidos);

        ButterKnife.bind(this);
        assistidosPresenter = new AssistidosPresenter(this);
        AssistidoDAO assistidoDAO = new AssistidoDAO(this);
        List<Assistido> assistidosList = assistidoDAO.getAssistidos();
        assistidosPresenter.updateList(assistidosList);

    }
    public void updateList(final List<Assistido> assistidosList) {

        //seta o adapter
        AssistidosAdapter assistidosAdapter = new AssistidosAdapter(assistidosList, this);

        assistidosAdapter.setOnRecyclerViewSelectedAssistidos(new OnRecyclerViewSelectedAssistidos() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AssistidosActivity.this,
                                AssistidosDetailActivity.class);
                intent.putExtra("assistido_id", assistidosList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(MoviesActivity.this, "Clique Longo", Toast.LENGTH_SHORT).show();
            }
        });

        rvAssistidos.setAdapter(assistidosAdapter);


        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAssistidos.setLayoutManager(layoutManager);
        rvAssistidos.addItemDecoration(dividerItemDecoration);
    }
}
