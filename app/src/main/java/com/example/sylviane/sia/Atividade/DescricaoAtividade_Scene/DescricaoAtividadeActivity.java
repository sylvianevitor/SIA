package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.ListaAtividades.AtividadesActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class DescricaoAtividadeActivity extends AppCompatActivity implements DescricaoAtividadeView{

    @BindView(R.id.camponomeatividade)EditText nomeAtividadeEditText;
    @BindView(R.id.campoobjetivoatividade) EditText objetivoAtividadeEditText;
    @BindView(R.id.campodescricaoatividade) EditText descricaoAtividadeEditText;
    @BindView(R.id.campodificuldadeatividade)Spinner dificuldadeAtividade;
    @BindView(R.id.campotemaatividade)Spinner temaAtividade;

    DescricaoAtividadePresenter descricaoAtividadePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);

        ButterKnife.bind(this);
        descricaoAtividadePresenter = new DescricaoAtividadePresenter(this); //MainActivity.this
    }

    @OnClick(R.id.botaocadastrardescricaoatividade)
    public void cadastro(){
        descricaoAtividadePresenter.cadastro();
    }

    @Override
    public void efetuaCadastro(){

        Atividade atividade = new Atividade();
        atividade.setNome(nomeAtividadeEditText.getText().toString());
        atividade.setObjetivo(objetivoAtividadeEditText.getText().toString());
        atividade.setDescricao(descricaoAtividadeEditText.getText().toString());
        atividade.setDificuldade(dificuldadeAtividade.getBaseline()); //deve estar errado
        atividade.setId_tema(temaAtividade.getBaseline()); //deve estar errado

        AtividadeDAO atividadeDAO = new AtividadeDAO(DescricaoAtividadeActivity.this);
        boolean ok = atividadeDAO.insert(atividade);

        Toast toast;
        if (ok == true) {
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Descrição de atividade cadastrada com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent abrirCriarTemplate1Activity = new Intent(DescricaoAtividadeActivity.this, CriarTemplate1Activity.class);
            startActivity(abrirCriarTemplate1Activity);

        } else{
            toast = Toast.makeText(DescricaoAtividadeActivity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}
