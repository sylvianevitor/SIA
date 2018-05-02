package com.example.sylviane.sia.SelecionarAtividade_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;
import com.example.sylviane.sia.Tema_Scene.Tema_Passivo.TemaPassivoActivity;

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
        setContentView(R.layout.activity_tipo_atividade);

        ButterKnife.bind(this);

        selecionarAtividadesPresenter = new SelecionarAtividadesPresenter(this);
    }

    @OnClick(R.id.button_atividade_interativa)
    public void selecionarAtividadeInterativa(){
        selecionarAtividadesPresenter.selecionarAtividadeInterativa();
    }

    @Override
    public void selecionarInterativa() {
        Intent abrirTemaInterativoActivity = new Intent(SelecionarAtividadesActivity.this, TemaInterativoActivity.class);
        startActivity(abrirTemaInterativoActivity);
    }

    @OnClick(R.id.button_atividade_passiva)
    public void selecionarAtividadePassiva(){
        selecionarAtividadesPresenter.selecionarAtividadePassiva();
    }

    @Override
    public void selecionarPassiva() {
        Intent abrirTemaPassivoActivity = new Intent(SelecionarAtividadesActivity.this, TemaPassivoActivity.class);
        startActivity(abrirTemaPassivoActivity);
    }
}
