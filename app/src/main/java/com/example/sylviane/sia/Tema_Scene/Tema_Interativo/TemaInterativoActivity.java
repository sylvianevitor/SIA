package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.ListaAtividades.AtividadesActivity;
import com.example.sylviane.sia.Tema_Scene.CadastrarTemas.CadastrarTemasInterativosActivity;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.dao.TemaDAO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mariana on 25/04/18.
 */

public class TemaInterativoActivity extends AppCompatActivity implements TemaInterativoView {

    @BindView(R.id.temas_list) RecyclerView rvTemas;

    TemaInterativoPresenter temaInterativoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_temas_list);

        ButterKnife.bind(this);

        temaInterativoPresenter = new TemaInterativoPresenter(this);

        TemaDAO temaDAO = new TemaDAO(TemaInterativoActivity.this);
        List<Tema> temaList = temaDAO.getTemas(); //buscar todos os temas no BD

        Log.d("LUAN", Integer.toString(temaList.size()));

        this.updateList(temaList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateList(final List<Tema> temaList) {

        //seta o adapter
        TemaInterativoAdapter temaInterativoAdapter = new TemaInterativoAdapter(temaList, this);
        temaInterativoAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent openListaAtividadesActivity = new Intent(TemaInterativoActivity.this, AtividadesActivity.class);
                openListaAtividadesActivity.putExtra("tema_id", temaList.get(position).getId());
                startActivity(openListaAtividadesActivity);
            }
        });

        rvTemas.setAdapter(temaInterativoAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemas.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvTemas.addItemDecoration(dividerItemDecoration);

    }


    @OnClick(R.id.botao_Adicionar_Tema)
    public void cadastrar(){
        Intent openCadastrarTemaInterativoActivity = new Intent(TemaInterativoActivity.this, CadastrarTemasInterativosActivity.class);
        startActivity(openCadastrarTemaInterativoActivity);
    }
}
