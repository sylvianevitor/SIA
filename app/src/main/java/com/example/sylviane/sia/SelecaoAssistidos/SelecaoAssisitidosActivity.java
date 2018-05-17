package com.example.sylviane.sia.SelecaoAssistidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sylviane.sia.AssistidosDetail.AssistidosDetailActivity;
import com.example.sylviane.sia.ListaAssistidos.AssistidosActivity;
import com.example.sylviane.sia.ListaAssistidos.OnRecyclerViewSelectedAssistidos;
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

    SelecaoAssistidosPresenter assistidosPresenter;
    AssistidoDAO assistidoDAO = new AssistidoDAO(SelecaoAssisitidosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistidos_item_list);


        List<Assistido> listaAssistidos = assistidoDAO.getAssistidos(); //lista do banco

        ButterKnife.bind(this);
        assistidosPresenter= new SelecaoAssistidosPresenter(this);
        assistidosPresenter.updateList(listaAssistidos);
        updateList(listaAssistidos);
    }

    public void updateList(final List<Assistido> assistidosList) {
        //seta o adapter
        SelecaoAssistidosAdapter assistidosAdapter = new SelecaoAssistidosAdapter(assistidosList,this);

        rvAssistidos.setAdapter(assistidosAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAssistidos.setLayoutManager(layoutManager);
        rvAssistidos.addItemDecoration(dividerItemDecoration);
    }

}

