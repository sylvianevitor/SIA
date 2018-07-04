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
    @BindView(R.id.campotipoatividade)Spinner tipoAtividade;
    boolean editar = false ;
    Atividade atividadeExistente;
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);

    DescricaoAtividadePresenter descricaoAtividadePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);
        TemaDAO temaDAO = new TemaDAO(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade",-1);
        Log.d("Atividade id", Integer.toString(id_atividade));

        if(id_atividade != -1) { //Obter campos possivelmente ja preenchidos
            preenchimento(id_atividade);
            atividadeExistente = atividadeDAO.getAtividadeId(id_atividade);
            editar = true;
        }

        descricaoAtividadePresenter = new DescricaoAtividadePresenter(this);

        //Preencher spinner dos temas
        ArrayAdapter<Tema> spinnerArrayAdapter = new ArrayAdapter<Tema>(
                this, android.R.layout.simple_spinner_item, temaDAO.getTemas());
        temaAtividade.setAdapter(spinnerArrayAdapter);
    }

    public void preenchimento(int id_atividade){
        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
        nomeAtividadeEditText.setText(atividade.getNome());
        objetivoAtividadeEditText.setText(atividade.getObjetivo());
        descricaoAtividadeEditText.setText(atividade.getDescricao());
    }

    @OnClick(R.id.botaocadastrardescricaoatividade)
    public void cadastro(){
        descricaoAtividadePresenter.cadastro();
    }

    @Override
    public void efetuaCadastro(){

        Atividade atividade = new Atividade();
        if (editar){
            atividade = atividadeExistente;
        }
        atividade.setNome(nomeAtividadeEditText.getText().toString());
        atividade.setObjetivo(objetivoAtividadeEditText.getText().toString());
        atividade.setDescricao(descricaoAtividadeEditText.getText().toString());
        atividade.setDt_cadastro("teste");
        atividade.setId_proprietario(1);
        atividade.setNr_execucoes(0);
        if(tipoAtividade.getSelectedItemPosition() == 0){
            atividade.setTipo_atividade(Atividade.TIPO_ATIVA);
        }else{ atividade.setTipo_atividade(Atividade.TIPO_PASSIVA);}
        atividade.setDificuldade(dificuldadeAtividade.getSelectedItemPosition());
        Tema t = (Tema) temaAtividade.getSelectedItem();
        atividade.setId_tema(t.getId());
        atividade.setAtiva(Atividade.SITUACAO_ATIVA);

        if (editar){ //editar cadastro caso ja exista e nao inserir uma nova
            Log.d("Editar atividade", Integer.toString(atividade.getId()));
            atividade = atividadeDAO.update(atividade);
        }else{atividade = atividadeDAO.insert(atividade);}

        Toast toast;

        if (atividade != null) {
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Descrição de atividade cadastrada com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent abrirCriarTemplate1Activity = new Intent(DescricaoAtividadeActivity.this, CriarTemplate1Activity.class);
            abrirCriarTemplate1Activity.putExtra("id_atividade", atividade.getId());
            startActivity(abrirCriarTemplate1Activity);
            finish();

        } else{
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
