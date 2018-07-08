package com.example.sylviane.sia.AssistidosDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Relatorios.RelatorioAssistidosActivity;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Execucao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssistidosDetailActivity extends AppCompatActivity implements AssistidosDetailView{
    @BindView(R.id.nome_assistido_detail)
    TextView nome_detail;
    @BindView(R.id.idade_assistido_detail)
    TextView idade_detail;
    @BindView(R.id.nome_responsavel_assistido_detail)
    TextView responsavel_detail;
    @BindView(R.id.telefone_assistido_detail)
    TextView telefone_detail;
    @BindView(R.id.outras_infos_assistido_detail)
    TextView outras_infos_detail;
    @BindView(R.id.medicamentos_assistido_detail)
    TextView medicamentos_detail;
    @BindView(R.id.rv_assistidos_detail)
    RecyclerView rvExecucoes;


    AssistidosDetailPresenter assistidosDetailPresenter;
    int assistidoId;
    ExecucaoDAO execucaoDAO = new ExecucaoDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistidos_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        assistidoId = intent.getIntExtra("assistido_id", -1);
        assistidosDetailPresenter = new AssistidosDetailPresenter(this,this);
        assistidosDetailPresenter.getAssistidosDetails(assistidoId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //List<Execucao> execucaoList = execucaoDAO.getExecucaosAssistido(assistidoId);
        //Log.d("Execucao recuperada", Integer.toString(execucaoList.get(0).getId_atividade()));
        //assistidosDetailPresenter.updateList(execucaoList);

    }

    public void updateListAtividades(final List<Execucao> atividadesExecutadasList) {
        //seta o adapter
        AssistidosDetailAdapter assistidosDetailAdapter = new AssistidosDetailAdapter(atividadesExecutadasList, this);

        assistidosDetailAdapter.setOnRecyclerViewSelectedExecucao(new OnRecyclerViewSelectedExecucao() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AssistidosDetailActivity.this,
                                RelatorioAssistidosActivity.class);
                intent.putExtra("id_assistido", assistidoId);
                intent.putExtra("id_execucao", atividadesExecutadasList.get(position).getId());
                startActivity(intent);
                finish();

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        });

        rvExecucoes.setAdapter(assistidosDetailAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvExecucoes.setLayoutManager(layoutManager);
        rvExecucoes.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showDetails(Assistido assistido) {
        nome_detail.setText(assistido.getNome_completo());
        responsavel_detail.setText(assistido.getResponsavel());
        telefone_detail.setText(assistido.getTelefone());
        outras_infos_detail.setText(assistido.getInformacoes());
        medicamentos_detail.setText(assistido.getMedicamentos());

        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(assistido.getDt_nasc());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idade_detail.setText(assistidosDetailPresenter.calculaIdade(date));
    }

    @Override
    public void showError() {
        Toast.makeText(this,"Erro ao pegar infos do banco",Toast.LENGTH_LONG);
    }

    @Override
    public String calculaIdade(Date dataNasc) {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        }
        else
        {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }

        return Integer.toString(idade);
    }

}