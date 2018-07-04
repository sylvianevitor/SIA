package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;

import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Tema;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarTemasInterativosActivity extends AppCompatActivity implements  CadastrarTemasInterativosView{

    CadastrarTemasInterativoPresenter cadastrarTemasInterativoPresenter;

    private int PICK_IMAGE_REQUEST = 1;
    private String imagePath;

    @BindView(R.id.text_input_layout_name)TextInputLayout nameTextInputLayout;
    @BindView(R.id.nome_tema)TextInputEditText nameEditText;
    @BindView(R.id.botao_camera)ImageButton imagem;

    private static final int REQUEST_CAMERA = 123;
    String selectedImagePath;
    String caminho_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_temas_interativos);

        ButterKnife.bind(this);
        selectedImagePath = new String();

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
        tema.setImagem(imagem.toString());

        TemaDAO temaDAO = new TemaDAO(CadastrarTemasInterativosActivity.this);
        boolean ok = temaDAO.insert(tema);

        Toast.makeText(getApplicationContext(), imagem.toString(), Toast.LENGTH_SHORT).show();

        Toast toast;
        if (ok == true) {
            toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Tema cadastrado com sucesso", Toast.LENGTH_LONG);
            toast.show();
            finish();

        } else{
            toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Impossível cadastrar o tema", Toast.LENGTH_LONG);
            toast.show();
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

}
