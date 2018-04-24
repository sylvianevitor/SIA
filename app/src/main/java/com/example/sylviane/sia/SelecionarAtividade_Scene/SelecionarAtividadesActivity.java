package com.example.sylviane.sia.SelecionarAtividade_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.Main_Scene.MainPresenter;
import com.example.sylviane.sia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mariana on 24/04/18.
 */

public class SelecionarAtividadesActivity extends AppCompatActivity implements SelecionarAtividadesView{

    SelecionarAtividadesPresenter selecionarAtividadesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        selecionarAtividadesPresenter = new SelecionarAtividadesPresenter(this);
    }

    @OnClick(R.id.button_atividade_interativa)
    public void selecionarAtividade(){
        selecionarAtividadesPresenter.criarAtividade();
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
