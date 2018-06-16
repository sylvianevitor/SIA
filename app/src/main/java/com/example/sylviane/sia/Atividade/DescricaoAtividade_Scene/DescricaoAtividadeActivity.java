package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DescricaoAtividadeActivity extends AppCompatActivity implements DescricaoAtividadeView{

    @BindView(R.id.camponomeatividade)EditText nomeAtividadeEditText;
    @BindView(R.id.campoobjetivoatividade) EditText objetivoAtividadeEditText;
    @BindView(R.id.campodescricaoatividade) EditText descricaoAtividadeEditText;
    @BindView(R.id.campodificuldadeatividade)Spinner dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)Spinner temaAtividade;

    DescricaoAtividadePresenter descricaoAtividadePresenter;
    TemaDAO temaDAO = new TemaDAO(this);
    //List<Tema> temasList = temaDAO.getTemas();
    String data[] = new String[4];
    Integer id_data[] = new Integer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);

        ButterKnife.bind(this);
        descricaoAtividadePresenter = new DescricaoAtividadePresenter(this); //MainActivity.this


        for (int i = 0; i < 4; i++){
            data[i] = String.valueOf(temaDAO.getTemas().get(i).getTema());
            id_data[i] = temaDAO.getTemas().get(i).getId();
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, data);
        temaAtividade.setAdapter(spinnerArrayAdapter);
    }

    @OnClick(R.id.botaocadastrardescricaoatividade)
    public void cadastro(){
        descricaoAtividadePresenter.cadastro();
    }

    @Override
    public void efetuaCadastro(){

        Atividade atividade = new Atividade();
        atividade.setNome(nomeAtividadeEditText.getText().toString());
        atividade.setObjetivo(objetivoAtividadeEditText.getText().toString());
        atividade.setDescricao(descricaoAtividadeEditText.getText().toString());

        atividade.setDificuldade(2);
        atividade.setDt_cadastro("teste");
        atividade.setId_proprietario(1);
        atividade.setId_tema(1);
        atividade.setNr_execucoes(0);
        atividade.setTipo_atividade(Atividade.TIPO_ATIVA);
        atividade.setDificuldade(dificuldadeAtividade.getSelectedItemPosition());
        atividade.setId_tema(id_data[temaAtividade.getSelectedItemPosition()]);
        //Log.d("Dificuldade selecionada", String.valueOf(dificuldadeAtividade.getSelectedItemPosition()));
        //Log.d("Tema id selecionado", String.valueOf(id_data[temaAtividade.getSelectedItemPosition()]));

        AtividadeDAO atividadeDAO = new AtividadeDAO(DescricaoAtividadeActivity.this);
        atividade = atividadeDAO.insert(atividade);

        Toast toast;

        if (atividade != null) {
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Descrição de atividade cadastrada com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent abrirCriarTemplate1Activity = new Intent(DescricaoAtividadeActivity.this, CriarTemplate1Activity.class);
            abrirCriarTemplate1Activity.putExtra("id_atividade", atividade.getId());
            startActivity(abrirCriarTemplate1Activity);

        } else{
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}
