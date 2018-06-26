package com.example.sylviane.sia.AtividadesDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Cores_Scene.ExecutarCoresActivity;
import com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene.DescricaoAtividadeActivity;
import com.example.sylviane.sia.Atividade.Template1_Scene.ExecutarTemplate1Activity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.CadastrarTemas.CadastrarTemasInterativosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Natasha on 25/04/2018.
 */

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
    @BindView(R.id.campodificuldadeatividade)
    TextView dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)
    TextView temaAtividade;
    @BindView(R.id.botaoIniciarAtividade)
    Button botaoiniciar;
    @BindView(R.id.botaoEditarAtividade)
    Button botaoEditar;
    @BindView(R.id.botaoExcluirAtividade)
    Button botaoExcluir;

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

        atividade = atividadeDAO.getAtividadeId(id_atividade);
        Log.d("Nome atividade", atividade.getNome());

        abreDescricao();
    }

    @OnClick(R.id.botaoIniciarAtividade)
    public void iniciar() {
        Log.d("Iniciar atividade", atividade.getNome());
        if (atividade.getId() == 1) {
            Intent ExecucaoCores = new Intent(AtividadesDetailActivity.this, ExecutarCoresActivity.class);
            ExecucaoCores.putExtra("id_atividade", atividade.getId());
            startActivity(ExecucaoCores);
        } else {
            Intent abrirExecucao = new Intent(AtividadesDetailActivity.this, ExecutarTemplate1Activity.class);
            abrirExecucao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirExecucao);
        }
    }
    @OnClick(R.id.botaoEditarAtividade)
    public void editar(){
        Log.d("Editar atividade", atividade.getNome());
        if (atividade.getId() == 1) {
            Toast toast;
            toast = Toast.makeText(AtividadesDetailActivity.this, "Impossivel editar atividade default", Toast.LENGTH_LONG);
            toast.show();
        } else{
            Intent abrirEdicao = new Intent(AtividadesDetailActivity.this, DescricaoAtividadeActivity.class);
            abrirEdicao.putExtra("id_atividade", atividade.getId());
            startActivity(abrirEdicao);
        }
    }
    @OnClick(R.id.botaoExcluirAtividade)
    public void excluir(){
        Toast toast;
        Log.d("Excluir atividade", atividade.getNome());
        if (atividade.getId() == 1) {
            toast = Toast.makeText(AtividadesDetailActivity.this, "Impossivel excluir atividade default", Toast.LENGTH_LONG);
            toast.show();
        } else{
           //excluir atividade
            toast = Toast.makeText(AtividadesDetailActivity.this, "Atividade excluida com sucesso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void abreDescricao() {
        nomeAtividade.setText(atividade.getNome());
        objetivoAtividade.setText(atividade.getObjetivo());
        descricaoAtividade.setText(atividade.getDescricao());
        dificuldadeAtividade.setText(Integer.toString(atividade.getDificuldade()));
        temaAtividade.setText(Integer.toString(atividade.getId_tema()));
    }

}
