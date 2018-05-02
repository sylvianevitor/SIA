package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class DescricaoAtividadeActivity extends AppCompatActivity implements DescricaoAtividadeView{

    @BindView(R.id.camponomeatividade)TextInputEditText nomeAtividadeEditText;
    @BindView(R.id.campoobjetivoatividade) TextInputEditText objetivoAtividadeEditText;
    @BindView(R.id.campodescricaoatividade) TextInputEditText descricaoAtividadeEditText;
    @BindView(R.id.campodificuldadeatividade)Spinner dificuldadeAtividadeEditText;
    @BindView(R.id.campotemaatividade)Spinner temaAtividadeEditText;

    DescricaoAtividadePresenter descricaoAtividadePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);

        ButterKnife.bind(this);

        descricaoAtividadePresenter = new DescricaoAtividadePresenter(this); //MainActivity.this
    }

    @OnClick(R.id.botaocadastrardescricaoatividade)
    public void cadastro(){
        descricaoAtividadePresenter.cadastro();
    }

    @Override
    public void efetuaCadastro(){
        Intent abrirCriarTemplate1Activity = new Intent(DescricaoAtividadeActivity.this, CriarTemplate1Activity.class);
        startActivity(abrirCriarTemplate1Activity);
    }
}
