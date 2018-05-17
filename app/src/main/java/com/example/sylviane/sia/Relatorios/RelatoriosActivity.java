package com.example.sylviane.sia.Relatorios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatoriosActivity extends AppCompatActivity implements RelatoriosView{

    RelatoriosPresenter relatoriosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        ButterKnife.bind(this);

        relatoriosPresenter = new RelatoriosPresenter(this);
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
