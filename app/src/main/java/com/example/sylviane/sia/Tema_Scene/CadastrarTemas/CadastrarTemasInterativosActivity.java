package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

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
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Presenter;
import com.example.sylviane.sia.Main_Scene.MainPresenter;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.com.example.sylviane.sia.persist.model.Tema;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarTemasInterativosActivity extends AppCompatActivity implements  CadastrarTemasInterativosView{

    CadastrarTemasInterativoPresenter cadastrarTemasInterativoPresenter;

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

        TemaDAO temaDAO = new TemaDAO(CadastrarTemasInterativosActivity.this);
        boolean ok = temaDAO.insert(tema);

        Toast toast;
        if (ok == true) {
            toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Tema cadastrado com sucesso", Toast.LENGTH_LONG);
            toast.show();

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
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamera.resolveActivity(getPackageManager()) != null) {
            selectedImagePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
            File arquivoFoto = new File(selectedImagePath);
            Uri fileUri = FileProvider.getUriForFile(this, "com.example.sylviane.sia.fileprovider", arquivoFoto);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            startActivityForResult(intentCamera, REQUEST_CAMERA);
        }else {
            Toast toast = Toast.makeText(CadastrarTemasInterativosActivity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            try {

                Glide.with(imagem.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagem) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(imagem.getContext().getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        imagem.setImageDrawable(circularBitmapDrawable);
//                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }
            caminho_foto = selectedImagePath;
        }
    }

}
