package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarTemasInterativosActivity extends AppCompatActivity implements Contract.View {

    Contract.Presenter cadastrarTemasInterativoPresenter;

    private int PICK_IMAGE_REQUEST = 1;

    @BindView(R.id.text_input_layout_name)TextInputLayout nameTextInputLayout;
    @BindView(R.id.nome_tema)TextInputEditText nameEditText;
    @BindView(R.id.botao_camera)ImageButton imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_temas_interativos);

        ButterKnife.bind(this);

        cadastrarTemasInterativoPresenter = new CadastrarTemasInterativoPresenter(this, this);

    }

    @OnClick(R.id.button_salvar_tema)
    public void salvar(){

        if (validar(nameEditText.getText().toString()) == false){return;}
        cadastrarTemasInterativoPresenter.salvarTema(nameEditText.getText().toString(), imagem.toString());
    }

    @Override
    public void abrirActivity(boolean ok) {
        if (ok) {
            Toast.makeText(CadastrarTemasInterativosActivity.this, "Tema cadastrado com sucesso", Toast.LENGTH_LONG).show();
            Intent abrirTemasInterativosActivity = new Intent(CadastrarTemasInterativosActivity.this, TemaInterativoActivity.class);
            startActivity(abrirTemasInterativosActivity);
            finish();

        } else{
            Toast.makeText(CadastrarTemasInterativosActivity.this, "Impossível cadastrar o tema", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.botao_camera)
    public void ligarCamera(){
        cadastrarTemasInterativoPresenter.ligarCamera();
    }

    @Override
    public void camera(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imagem.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean validar(String nome){
        if (TextUtils.isEmpty(nome)) {
            nameTextInputLayout.setErrorEnabled(true);
            nameTextInputLayout.setError("Nome inválido");
            return false;
        }else if (nome.matches("^[a-zA-Z\\u00C0-\\u00FF]*$") == false){
            nameTextInputLayout.setErrorEnabled(true);
            nameTextInputLayout.setError("Nome deve conter letras");
            return false;
        }
        //else if (cadastrarTemasInterativoPresenter.comparaTema(nome)){
//            nameTextInputLayout.setErrorEnabled(true);
//            nameTextInputLayout.setError("Tema já existe");
//            return;}
        return true;
    }


}
