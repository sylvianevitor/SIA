package com.example.sylviane.sia.AtividadesDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Atividade_Passiva.ExecutarAtividadePassiva.ExecutarAtividadePassivaActivity;
import com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene.DescricaoAtividadeActivity;
import com.example.sylviane.sia.ListaAtividades.AtividadesActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.SelecaoAssistidos.SelecaoAssisitidosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AtividadesDetailActivity extends AppCompatActivity implements AtividadesDetailView {
    Atividade atividade = new Atividade();
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);
    Integer id_atividade;

    @BindView(R.id.camponome)
    TextView nomeAtividade;
    @BindView(R.id.campoobjetivo)
    TextView objetivoAtividade;
    @BindView(R.id.campodescricao)
    TextView descricaoAtividade;
    @BindView(R.id.campodificuldadeativ)
    TextView dificuldadeAtividade;
    @BindView(R.id.campotemaativ)
    TextView temaAtividade;
    @BindView(R.id.botaoIniciarAtividade)
    Button botaoiniciar;
    @BindView(R.id.botaoEditarAtividade)
    Button botaoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_atividade);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            id_atividade = extras.getInt("atividade_id");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        atividade = atividadeDAO.getAtividadeId(id_atividade);
        setTitle(atividade.getNome());
        abreDescricao();
    }

    @OnClick(R.id.botaoIniciarAtividade)
    public void iniciar() {
        if(atividade.getTipo_atividade()==Atividade.TIPO_ATIVA) {
            Intent abrirExecucao = new Intent(AtividadesDetailActivity.this, SelecaoAssisitidosActivity.class);
            abrirExecucao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirExecucao);
            finish();
        }
        else{
            Intent abrirExecucao = new Intent(AtividadesDetailActivity.this, ExecutarAtividadePassivaActivity.class);
            abrirExecucao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirExecucao);
            finish();

        }
    }

    @OnClick(R.id.botaoExcluirAtividade)
    public void excluir() {
        Toast toast;
        if (atividade.getId() == 1) {
            toast = Toast.makeText(AtividadesDetailActivity.this, "Impossivel excluir atividade default", Toast.LENGTH_LONG);
            toast.show();
        } else {
            atividade.setAtiva(Atividade.SITUACAO_INATIVA); //"EXCLUIR"
            atividadeDAO.update(atividade);
            toast = Toast.makeText(AtividadesDetailActivity.this, "Atividade excluida com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent retornar = new Intent(AtividadesDetailActivity.this, AtividadesActivity.class);
            startActivity(retornar);
            finish();
        }
    }
    @OnClick(R.id.botaoEditarAtividade)
    public void editar(){
        if (atividade.getId() == 1) {
            Toast toast;
            toast = Toast.makeText(AtividadesDetailActivity.this, "Impossivel editar atividade default", Toast.LENGTH_LONG);
            toast.show();
        } else{
            Intent abrirEdicao = new Intent(AtividadesDetailActivity.this, DescricaoAtividadeActivity.class);
            abrirEdicao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirEdicao);
            finish();
        }
    }

    @Override
    public void abreDescricao() {
        nomeAtividade.setText(atividade.getNome());
        objetivoAtividade.setText(atividade.getObjetivo());
        descricaoAtividade.setText(atividade.getDescricao());
        nomeAtividade.setText(atividade.getNome());
        objetivoAtividade.setText(atividade.getObjetivo());
        descricaoAtividade.setText(atividade.getDescricao());
        dificuldadeAtividade.setText(Integer.toString(atividade.getDificuldade()));
        temaAtividade.setText(Integer.toString(atividade.getId_tema()));
    }

}
