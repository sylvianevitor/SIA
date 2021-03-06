package com.example.sylviane.sia.SelecionarAtividade_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;
import com.example.sylviane.sia.persist.model.Atividade;

import butterknife.ButterKnife;
import butterknife.OnClick;


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
        abrirTemaInterativoActivity.putExtra("tipo_atividade", Atividade.TIPO_ATIVA);
        startActivity(abrirTemaInterativoActivity);
        finish();
    }

    @OnClick(R.id.button_atividade_passiva)
    public void selecionarAtividadePassiva(){
        selecionarAtividadesPresenter.selecionarAtividadePassiva();
    }

    @Override
    public void selecionarPassiva() {
        Intent abrirTemaInterativoActivity = new Intent(SelecionarAtividadesActivity.this, TemaInterativoActivity.class);
        abrirTemaInterativoActivity.putExtra("tipo_atividade", Atividade.TIPO_PASSIVA);
        startActivity(abrirTemaInterativoActivity);
        finish();
    }
}
