package com.example.sylviane.sia.Relatorios;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatorioAssistidosActivity extends AppCompatActivity implements RelatorioAssistidosView{

    @BindView(R.id.nome_atividade)TextView nome_atividade;
    @BindView(R.id.data_execucao)TextView data_execucao;
    @BindView(R.id.text_data)TextView text_data;
    @BindView(R.id.text_horario)TextView text_horario;
    @BindView(R.id.dificuldade_atividade)TextView dificuldade_atividade;
    @BindView(R.id.text_dificuldade)TextView text_dificuldade;
    @BindView(R.id.porcentagem_acertos)TextView porcentagem_acertos;
    @BindView(R.id.text_porcentagem)TextView text_porcentagem;
    @BindView(R.id.tempo_execucao)TextView tempo_execucao;
    @BindView(R.id.text_tempo)TextView text_tempo;
    @BindView(R.id.observacoes)TextView observacoes;
    @BindView(R.id.campo_observacoes)TextInputEditText campo_observacoesEditText;

    RelatorioAssistidosPresenter relatorioAssistidosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_assistidos);

        ButterKnife.bind(this);

        relatorioAssistidosPresenter = new RelatorioAssistidosPresenter(this);
    }

    @OnClick(R.id.botao_sair)
    public void sair_relatorios(){
        relatorioAssistidosPresenter.voltar_tela_inicial();
    }

    @Override
    public void sair() {
        Intent voltarMainActivity = new Intent(RelatorioAssistidosActivity.this, MainActivity.class);
        startActivity(voltarMainActivity);
    }
}
