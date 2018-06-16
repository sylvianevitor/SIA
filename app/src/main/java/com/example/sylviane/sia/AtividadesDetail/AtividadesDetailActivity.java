package com.example.sylviane.sia.AtividadesDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import butterknife.BindView;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesDetailActivity extends Activity {
    Atividade atividade = new Atividade();
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);
    Integer id_atividade;

    @BindView(R.id.camponome)TextView nomeAtividade;
    @BindView(R.id.campoobjetivo) TextView objetivoAtividade;
    @BindView(R.id.campodescricao) TextView descricaoAtividade;
    @BindView(R.id.campodificuldadeatividade)Spinner dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)Spinner temaAtividade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
           id_atividade = extras.getInt("atividade_id");
        }

        setContentView(R.layout.activity_detail_atividade);

        atividade = atividadeDAO.getAtividadeId(id_atividade);

        Log.d("Nome atividade", atividade.getNome());

       /* nomeAtividade.setText(atividade.getNome());
        objetivoAtividade.setText(atividade.getObjetivo());
        descricaoAtividade.setText(atividade.getDescricao());*/

    }
}
