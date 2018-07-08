package com.example.sylviane.sia.Relatorios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Execucao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatorioAssistidosActivity extends AppCompatActivity implements RelatorioAssistidosView{

    @BindView(R.id.nome_atividade)TextView nome_atividade;
    @BindView(R.id.text_data)TextView text_data;
    @BindView(R.id.text_horario)TextView text_horario;
    @BindView(R.id.text_dificuldade)TextView text_dificuldade;
    @BindView(R.id.text_porcentagem)TextView text_porcentagem;
    @BindView(R.id.text_tempo)TextView text_tempo;
    @BindView(R.id.text_observacoes)TextView text_observacoes;

    RelatorioAssistidosPresenter relatorioAssistidosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_assistidos);

        ButterKnife.bind(this);

        relatorioAssistidosPresenter = new RelatorioAssistidosPresenter(this);

        //POR ENQUANTO ESSES SAO VALORES EXEMPLO, DEPOIS QUE TIVER O BANCO DE DADOS VAI MUDAR

        //CARREGA NOME DO BANCO DE DADOS
//        nome_atividade.setText("Nome Teste");
//
//        //CARREGA DATA DO BANCO DE DADOS
//        text_data.setText("01/01/01");
//
//        //CARREGA HORARIO DO BANCO DE DADOS
//        text_horario.setText("21:00");
//
//        //CARREGA DIFICULDADE DO BANCO DE DADOS
//        text_dificuldade.setText("Alta");
//
//        //CARREGA PORCENTAGEM DO BANCO DE DADOS
//        text_porcentagem.setText("50%");
//
//        //CARREGA TEMPO DE EXECUCAO DO BANCO DE DADOS
//        text_tempo.setText("02 minutos");
//
//        //CARREGA OBSERVACOES DE EXECUCAO DO BANCO DE DADOS
//        text_observacoes.setText("O aluno foi muito bem na atividade, pisa menos");

        // Inicio da implementacao do banco

        Intent intent = getIntent();
        int assistidoId = intent.getIntExtra("id_assistido", -1);
        int execucaoId = intent.getIntExtra("id_execucao", -1);
        Log.d("ASSISTIDO ID", String.valueOf(assistidoId));
        Log.d("EXECUCAO ID", String.valueOf(execucaoId));

        carregarConteudo(execucaoId);
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

    public void carregarConteudo(int execucaoId){

        ExecucaoDAO execucaoDAO = new ExecucaoDAO(this);
        Execucao exec = execucaoDAO.getExecucaoId(execucaoId);
        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        Atividade atividade = atividadeDAO.getAtividadeId(exec.getId_atividade());

        nome_atividade.setText(atividade.getNome());
        Log.d("DATA", exec.getData());
        //text_data.setText(exec.getData());
        text_horario.setText(exec.getHora());
        //text_dificuldade.setText(atividade.getDificuldade());
        text_porcentagem.setText(Float.toString(exec.getPerc_acertos()));
        text_tempo.setText(Float.toString(exec.getTempo()));
        text_observacoes.setText(exec.getObservacao());
    }
}
