package com.example.sylviane.sia.AtividadesDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sylviane.sia.Atividade.Cores_Scene.ExecutarCoresActivity;
import com.example.sylviane.sia.Atividade.Template1_Scene.ExecutarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesDetailActivity extends AppCompatActivity implements AtividadesDetailView {
    Atividade atividade = new Atividade();
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);
    Integer id_atividade;

    @BindView(R.id.camponome)
    TextView nomeAtividade;
    @BindView(R.id.campoobjetivo)
    TextView objetivoAtividade;
    @BindView(R.id.campodescricao)
    TextView descricaoAtividade;
    @BindView(R.id.campodificuldadeatividade)
    Spinner dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)
    Spinner temaAtividade;
    @BindView(R.id.botaoIniciarAtividade)
    Button botaoiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_atividade);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            id_atividade = extras.getInt("atividade_id");
        }

        atividade = atividadeDAO.getAtividadeId(id_atividade);
        Log.d("Nome atividade", atividade.getNome());

        abreDescricao();
    }

    @OnClick(R.id.botaoIniciarAtividade)
    public void iniciar() {
        Log.d("Iniciar atividade", atividade.getNome());
        //Log.d("Id da atividade", Integer.toString(atividade.getId()));
        if (atividade.getId() == 1) {
            Intent ExecucaoCores = new Intent(AtividadesDetailActivity.this, ExecutarCoresActivity.class);
            ExecucaoCores.putExtra("id_atividade", atividade.getId());
            startActivity(ExecucaoCores);
        } else {
            Intent abrirExecucao = new Intent(AtividadesDetailActivity.this, ExecutarTemplate1Activity.class);
            abrirExecucao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirExecucao);
        }
    }

    @Override
    public void abreDescricao() {
        nomeAtividade.setText(atividade.getNome());
        objetivoAtividade.setText(atividade.getObjetivo());
        descricaoAtividade.setText(atividade.getDescricao());
    }

}
