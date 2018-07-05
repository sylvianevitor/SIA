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
import android.widget.Button;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.ListaAtividades.AtividadesActivity;
import com.example.sylviane.sia.Tema_Scene.CadastrarTemas.CadastrarTemasInterativosActivity;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.dao.TemaDAO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mariana on 25/04/18.
 */

public class TemaInterativoActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.temas_list) RecyclerView rvTemas;

    int tipo_atividade;
    Contract.Presenter temaInterativoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_temas_list);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        tipo_atividade = intent.getIntExtra("tipo_atividade",-1);

        temaInterativoPresenter = new TemaInterativoPresenter(this, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        temaInterativoPresenter.getTema();
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
                openListaAtividadesActivity.putExtra("tipo_atividade", tipo_atividade);
                openListaAtividadesActivity.putExtra("tema_id", temaList.get(position).getId());
                Log.d("ID TEMA", String.valueOf(temaList.get(position).getId()));
                startActivity(openListaAtividadesActivity);
                finish();
            }
        });

        rvTemas.setAdapter(temaInterativoAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTemas.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvTemas.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_temas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                temaInterativoPresenter.cadastrarTema();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void cadastrar(){
        Intent openCadastrarTemaInterativoActivity = new Intent(TemaInterativoActivity.this, CadastrarTemasInterativosActivity.class);
        startActivity(openCadastrarTemaInterativoActivity);
        finish();
    }

}
