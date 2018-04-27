package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sylviane.sia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mariana on 25/04/18.
 */

public class TemaInterativoActivity extends AppCompatActivity implements TemaInterativoView {

    @BindView(R.id.temas_list)
    RecyclerView rvTemas;

   // private BancoDados bancoDados;

    TemaInterativoPresenter temaInterativoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String jsonSocial = getIntent().getStringExtra("json_social");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_temas);

        ButterKnife.bind(this);

        temaInterativoPresenter = new TemaInterativoPresenter(this);
        temaInterativoPresenter.updateList(jsonSocial); //passar os dados no banco de dados

//        bancoDados = new BancoDados(conexaoBanco);
//        List<TemaInterativoEntity> temaList = bancoDados.buscarTodos(); //buscar todos os temas no BD
//
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateList(final List<TemaInterativoEntity> temaList) {

        //seta o adapter
        TemaInterativoAdapter temaInterativoAdapter = new TemaInterativoAdapter(temaList, this);
        temaInterativoAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent openListaAtividadesActivity = new Intent(TemaInterativoActivity.this, ListaAtividadesActivity.class);
                openListaAtividadesActivity.putExtra("tema", temaInterativoPresenter.getTemaId(position));
                startActivity(openListaAtividadesActivity);
            }
        });

        rvTemas.setAdapter(temaInterativoAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemas.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvTemas.addItemDecoration(dividerItemDecoration);

    }
}
