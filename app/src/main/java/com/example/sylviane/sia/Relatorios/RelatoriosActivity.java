package com.example.sylviane.sia.Relatorios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatoriosActivity extends AppCompatActivity implements RelatoriosView{

    @BindView(R.id.total_pontos)TextView total_pontos;
    @BindView(R.id.text_total_pontos)TextView text_total_pontos;

    RelatoriosPresenter relatoriosPresenter;
    private ExecucaoDAO execucaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        ButterKnife.bind(this);

        relatoriosPresenter = new RelatoriosPresenter(this);
        execucaoDAO = new ExecucaoDAO(this);

        //PRECISA DA FUNCAO DO BANCO DE DADOS PRA PEGAR ESSES PONTOS

        text_total_pontos.setText("9 pontos");
    }

    @OnClick(R.id.botao_sair)
    public void sair_relatorios(){
       relatoriosPresenter.voltar_tela_inicial();
    }

    @Override
    public void sair() {
        Intent voltarMainActivity = new Intent(RelatoriosActivity.this, MainActivity.class);
        startActivity(voltarMainActivity);
    }
}
