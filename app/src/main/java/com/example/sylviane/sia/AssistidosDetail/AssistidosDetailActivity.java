package com.example.sylviane.sia.AssistidosDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
//    @BindView(R.id.imagem_assistido_detail)
//    ImageView imagem_detail;
    @BindView(R.id.telefone_assistido_detail)
    TextView telefone_detail;
    @BindView(R.id.outras_infos_assistido_detail)
    TextView outras_infos_detail;
    @BindView(R.id.medicamentos_assistido_detail)
    TextView medicamentos_detail;
    @BindView(R.id.btnrelatorio)
    Button botaorelatorio;

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
    @OnClick(R.id.btnrelatorio)
    public void mostraRelatorio(){
        //mostrar relatorio
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