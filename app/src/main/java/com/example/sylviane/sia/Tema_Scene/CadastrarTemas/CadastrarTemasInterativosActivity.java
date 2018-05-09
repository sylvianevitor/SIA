package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.sylviane.sia.Main_Scene.MainPresenter;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.com.example.sylviane.sia.persist.model.Tema;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarTemasInterativosActivity extends AppCompatActivity implements  CadastrarTemasInterativosView{

    CadastrarTemasInterativoPresenter cadastrarTemasInterativoPresenter;

    @BindView(R.id.text_input_layout_name)TextInputLayout nameTextInputLayout;
    @BindView(R.id.nome_tema)TextInputEditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_temas_interativos);

        ButterKnife.bind(this);

        cadastrarTemasInterativoPresenter = new CadastrarTemasInterativoPresenter(this);

    }

    @OnClick(R.id.button_salvar_tema)
    public void salvarTema(){
        cadastrarTemasInterativoPresenter.salvarTema();
    }

    @Override
    public void salvar() {

        if (TextUtils.isEmpty(nameEditText.getText().toString())) {
            nameTextInputLayout.setErrorEnabled(true);
            nameTextInputLayout.setError("Nome inválido");
            return;
        }

        Tema tema = new Tema();
        tema.setTema(nameEditText.getText().toString());

        TemaDAO temaDAO = new TemaDAO(CadastrarTemasInterativosActivity.this);
        boolean ok = temaDAO.insert(tema);

        Toast toast;
        if(ok)
            toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Tema cadastrado com sucesso", Toast.LENGTH_LONG);
        else
            toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Impossível cadastrar o tema", Toast.LENGTH_LONG);

        toast.show();

        //Intent abrirCriarAtividadesActivity = new Intent(MainActivity.this, CriarAtividadesActivity.class);
        //startActivity(abrirCriarAtividadesActivity);
    }
}
