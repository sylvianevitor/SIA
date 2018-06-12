package com.example.sylviane.sia.AssistidosDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AssistidosDetailActivity extends AppCompatActivity implements AssistidosDetailView{
    @BindView(R.id.nome_assistido_detail)
    TextView nome_detail;
    @BindView(R.id.idade_assistido_detail)
    TextView idade_detail;
    @BindView(R.id.nome_responsavel_assistido_detail)
    TextView responsavel_detail;
    @BindView(R.id.imagem_assistido_detail)
    ImageView imagem_detail;
    @BindView(R.id.telefone_assistido_detail)
    TextView telefone_detail;
    @BindView(R.id.outras_infos_assistido_detail)
    TextView outras_infos_detail;
    @BindView(R.id.medicamentos_assistido_detail)
    TextView medicamentos_detail;

    AssistidosDetailPresenter assistidosDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistidos_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        int assistidoId = intent.getIntExtra("assistido_id", -1);


        assistidosDetailPresenter = new AssistidosDetailPresenter(this,this);
        assistidosDetailPresenter.getAssistidosDetails(assistidoId);
    }
    @Override
    public void showDetails(Assistido assistido) {
        nome_detail.setText(assistido.getNome_completo());
        responsavel_detail.setText(assistido.getResponsavel());
        telefone_detail.setText(assistido.getTelefone());
        outras_infos_detail.setText(assistido.getInformacoes());
        medicamentos_detail.setText(assistido.getMedicamentos());
        //Picasso.with(this)
        //        .load(assistido.getImagemUrl())
        //        .centerCrop()
        //        .fit()
        //        .into(imagem_detail);
    }

    @Override
    public void showError() {
        Toast.makeText(this,"Erro ao pegar infos do banco",Toast.LENGTH_LONG);
    }
}