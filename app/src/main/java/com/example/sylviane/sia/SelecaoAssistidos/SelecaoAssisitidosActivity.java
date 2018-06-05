package com.example.sylviane.sia.SelecaoAssistidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Template1_Scene.ExecutarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sylviane on 14/05/18.
 */

public class SelecaoAssisitidosActivity  extends AppCompatActivity implements SelecaoAssistidosView {

    @BindView(R.id.rvAssistidos)
    RecyclerView rvAssistidos;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    private int selectedPos = RecyclerView.NO_POSITION;

    SelecaoAssistidosPresenter assistidosPresenter;
    AssistidoDAO assistidoDAO = new AssistidoDAO(SelecaoAssisitidosActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_assistidos);
        ButterKnife.bind(this);
        assistidosPresenter= new SelecaoAssistidosPresenter(this);
        assistidoDAO.popularAssistidos();
        List<Assistido> listaAssistidos = assistidoDAO.getAssistidos(); //lista do banco
        this.updateList(listaAssistidos);
    }

    public void updateList(final List<Assistido> assistidosList) {
        //seta o adapter
        final SelecaoAssistidosAdapter assistidosAdapter = new SelecaoAssistidosAdapter(assistidosList, this);

        assistidosAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelectedAssistido() {
            @Override
            public void onClick(View view, int position) {
                assistidosAdapter.notifyItemChanged(position);
                Intent intent = new Intent
                        (SelecaoAssisitidosActivity.this, ExecutarTemplate1Activity.class); //trocar para AtividadesActivity
                //salvar no banco assistido selecionado
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
        rvAssistidos.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAssistidos.addItemDecoration(dividerItemDecoration);
    }

}

