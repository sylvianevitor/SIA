package com.example.sylviane.sia.SelecaoAssistidos;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Cores_Scene.ExecutarCoresActivity;
import com.example.sylviane.sia.Atividade.Template1_Scene.ExecutarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

import java.util.ArrayList;
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

    private ArrayList<Integer> idAssistidosList = new ArrayList<Integer>();

    SelecaoAssistidosPresenter assistidosPresenter;
    AssistidoDAO assistidoDAO = new AssistidoDAO(SelecaoAssisitidosActivity.this);

    int id_assistido, id_atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_assistidos);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        id_atividade = intent.getIntExtra("id_atividade",0);
        //Log.d("ID ATIVIDADE", String.valueOf(id_atividade));

        assistidosPresenter= new SelecaoAssistidosPresenter(this);
        List<Assistido> listaAssistidos = assistidoDAO.getAssistidos();

        this.updateList(listaAssistidos);
    }

    public void updateList(final List<Assistido> assistidosList) {
        //seta o adapter
        final SelecaoAssistidosAdapter assistidosAdapter = new SelecaoAssistidosAdapter(assistidosList, this);

        assistidosAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelectedAssistido() {
            @Override
            public void onClick(View view, int position, boolean state) {
                id_assistido = assistidosList.get(position).getId();
                Log.d("ID ASSISTIDO", String.valueOf(id_assistido));
                if(state == true){
                    adicionar(id_assistido);
                }else{
                    remover(idAssistidosList, id_assistido);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_iniciar_atividade, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                iniciar();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void iniciar(){
        if (id_atividade == 1) {
            Log.d("Iniciar atividade", "Atividade cores");
            Intent ExecucaoCores = new Intent(SelecaoAssisitidosActivity.this, ExecutarCoresActivity.class);
            ExecucaoCores.putExtra("id_atividade", id_atividade);
            ExecucaoCores.putExtra("assistido_id", idAssistidosList);
            startActivity(ExecucaoCores);
            finish();
        }else{
        Intent intent = new Intent (SelecaoAssisitidosActivity.this, ExecutarTemplate1Activity.class);
        intent.putExtra("id_atividade", id_atividade);
        intent.putExtra("assistido_id", idAssistidosList);
        startActivity(intent);
        finish();}
    }

    public void adicionar(int id_assistido){
        idAssistidosList.add(id_assistido);
        Log.d("LISTA IDs", String.valueOf(idAssistidosList));
    }

    public void remover(ArrayList<Integer> idAssistidosList, int id_assistido){
        int posicao = 0;

        for(int i = 0; i < idAssistidosList.size(); i++){
            if (idAssistidosList.get(i).equals(id_assistido)){
                posicao = i;
            }
        }

        idAssistidosList.remove(posicao);

        Log.d("LISTA IDs", String.valueOf(idAssistidosList));
    }

}

