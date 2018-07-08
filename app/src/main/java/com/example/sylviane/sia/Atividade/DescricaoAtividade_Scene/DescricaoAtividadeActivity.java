package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva.CriarAtividadePassivaActivity;
import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.model.Tema;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DescricaoAtividadeActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.camponomeatividade)EditText nomeAtividadeEditText;
    @BindView(R.id.campoobjetivoatividade) EditText objetivoAtividadeEditText;
    @BindView(R.id.campodescricaoatividade) EditText descricaoAtividadeEditText;
    @BindView(R.id.campodificuldadeatividade)Spinner dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)Spinner temaAtividade;
    @BindView(R.id.campotipoatividade)Spinner tipoAtividade;

    Contract.Presenter descricaoAtividadePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade",-1);

        descricaoAtividadePresenter = new DescricaoAtividadePresenter(this, this);
        descricaoAtividadePresenter.getTemas();
        descricaoAtividadePresenter.getAtividade(id_atividade);

    }

    public void preencheSpinnerTemas(ArrayList temas){
        //Preencher spinner dos temas
        ArrayAdapter<Tema> spinnerArrayAdapter = new ArrayAdapter<Tema>(
                this, android.R.layout.simple_spinner_item, temas);
        temaAtividade.setAdapter(spinnerArrayAdapter);
    }

    public void preenchimento(String nome, String objetivo, String descricao){
        nomeAtividadeEditText.setText(nome);
        objetivoAtividadeEditText.setText(objetivo);
        descricaoAtividadeEditText.setText(descricao);
    }

    @OnClick(R.id.botaocadastrardescricaoatividade)
    public void cadastro(){

        if (validar(nomeAtividadeEditText.getText().toString(), objetivoAtividadeEditText.getText().toString(), descricaoAtividadeEditText.getText().toString()) == false){return;}
        descricaoAtividadePresenter.cadastro(nomeAtividadeEditText.getText().toString(),
                objetivoAtividadeEditText.getText().toString(),
                descricaoAtividadeEditText.getText().toString(),
                dificuldadeAtividade.getSelectedItemPosition() + 1,
                (Tema) temaAtividade.getSelectedItem(), tipoAtividade.getSelectedItemPosition());
    }

    @Override
    public void abrirAtividade(int id_atividade){

        if (tipoAtividade.getSelectedItemPosition() == 1){
            Intent abrirPassiva = new Intent(DescricaoAtividadeActivity.this, CriarAtividadePassivaActivity.class);
            abrirPassiva.putExtra("id_atividade", id_atividade);
            startActivity(abrirPassiva);
        }
        else{
            Intent abrirCriarTemplate1Activity = new Intent(DescricaoAtividadeActivity.this, CriarTemplate1Activity.class);
            abrirCriarTemplate1Activity.putExtra("id_atividade", id_atividade);
            startActivity(abrirCriarTemplate1Activity);
        }

        finish();

    }
    public boolean validar(String nome1, String nome2, String nome3){
        if (TextUtils.isEmpty(nome1)) {
            nomeAtividadeEditText.setError("Nome inválido");
            return false;
        }else if (nome1.matches("^[a-zA-Z0-9 \\u00C0-\\u00FF]*$") == false){
            nomeAtividadeEditText.setError("Nome deve conter letras");
            return false;
        }else if (descricaoAtividadePresenter.comparaNome(nome1)== false){
            nomeAtividadeEditText.setError("Atividade já existe");
            return false;
        }

        if (TextUtils.isEmpty(nome2)) {
            objetivoAtividadeEditText.setError("Objetivo inválido");
            return false;
        }else if (nome2.matches("^[a-zA-Z0-9 \\u00C0-\\u00FF]*$") == false){
            objetivoAtividadeEditText.setError("Objetivo deve conter letras");
            return false;}

        if (TextUtils.isEmpty(nome3)) {
            descricaoAtividadeEditText.setError("Descrição inválida");
            return false;
        }else if (nome3.matches("^[a-zA-Z0-9 \\u00C0-\\u00FF]*$") == false){
            nomeAtividadeEditText.setError("Descrição deve conter letras");
            return false;}

        if(dificuldadeAtividade.getSelectedItem() == null){
            Toast.makeText(DescricaoAtividadeActivity.this, "Necessário selecionar dificuldade", Toast.LENGTH_LONG).show();
            return false;
        }

        if(temaAtividade.getSelectedItem() == null){
            Toast.makeText(DescricaoAtividadeActivity.this, "Necessário selecionar tema", Toast.LENGTH_LONG).show();
            return false;
        }

        if(tipoAtividade.getSelectedItem() == null){
            Toast.makeText(DescricaoAtividadeActivity.this, "Necessário selecionar tipo", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}
