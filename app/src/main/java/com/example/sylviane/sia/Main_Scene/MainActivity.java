package com.example.sylviane.sia.Main_Scene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sylviane.sia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
    }

    @OnClick(R.id.button_criar_atividade)
    public void criarAtividade(){
        mainPresenter.criarAtividade();
    }

    @Override
    public void criar() {
        Intent abrirCriarAtividadesActivity = new Intent(MainActivity.this, CriarAtividadesActivity.class);
        startActivity(abrirCriarAtividadesActivity);
    }

    @OnClick(R.id.button_selecionar_atividade)
    public void selecionarAtividade(){
        mainPresenter.selecionarAtividade();
    }

    @Override
    public void selecionar() {
        Intent abrirSelecionarAtividadesActivity = new Intent(MainActivity.this, SelecionarAtividadesActivity.class);
        startActivity(abrirSelecionarAtividadesActivity);
    }

    @OnClick(R.id.button_ver_assistidos)
    public void verAssistidos(){
        mainPresenter.verAssistidos();
    }

    @Override
    public void ver() {
        Intent abrirSelecionarAssistidosActivity = new Intent(MainActivity.this, SelecionarAssistidosActivity.class);
        startActivity(abrirSelecionarAssistidosActivity);
    }
}
