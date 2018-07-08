package com.example.sylviane.sia.Relatorios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.model.Execucao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatoriosActivity extends AppCompatActivity implements RelatoriosView{

    int id_execucao;
    @BindView(R.id.total_pontos)TextView total_pontos;
    @BindView(R.id.text_total_pontos)TextView text_total_pontos;
    @BindView(R.id.text_observacoes) EditText text_observacao;

    RelatoriosPresenter relatoriosPresenter;
    private ExecucaoDAO execucaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int pontuacao = intent.getIntExtra("pontos", 0);
        id_execucao = intent.getIntExtra("id_execucao",0);

        relatoriosPresenter = new RelatoriosPresenter(this);
        execucaoDAO = new ExecucaoDAO(this);
        text_total_pontos.setText(Integer.toString(pontuacao));
    }

    @OnClick(R.id.botao_sair)
    public void sair_relatorios(){
       relatoriosPresenter.voltar_tela_inicial();
    }

    @Override
    public void sair() {
        Intent voltarMainActivity = new Intent(RelatoriosActivity.this, MainActivity.class);
        Execucao execucao = execucaoDAO.getExecucaoId(id_execucao);
        execucao.setObservacao(text_observacao.getText().toString());
        execucaoDAO.update(execucao);
        startActivity(voltarMainActivity);
        finish();
    }
}
