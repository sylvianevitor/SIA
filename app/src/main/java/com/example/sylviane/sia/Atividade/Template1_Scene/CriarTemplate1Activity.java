package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene.DescricaoAtividadePresenter;
import com.example.sylviane.sia.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarTemplate1Activity extends AppCompatActivity implements CriarTemplate1View{

    @BindView(R.id.imageButton1)ImageView imageView1;

    private static final int REQUEST_CAMERA = 123;

    CriarTemplate1Presenter criarTemplate1Presenter;

    String selectedImagePath;
    String caminho_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_template1);

        ButterKnife.bind(this);
        selectedImagePath = new String();

        criarTemplate1Presenter = new CriarTemplate1Presenter(this); //MainActivity.this

    }

    @OnClick(R.id.imageButton1)
    public void ligarCamera(){
        criarTemplate1Presenter.ligarCamera();
    }

    @Override
    public void camera(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamera.resolveActivity(getPackageManager()) != null) {
            selectedImagePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
            File arquivoFoto = new File(selectedImagePath);
            Uri fileUri = FileProvider.getUriForFile(this, "com.edu.rafaelsaito.debu.fileprovider", arquivoFoto);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            startActivityForResult(intentCamera, REQUEST_CAMERA);
        }else {
            Toast toast = Toast.makeText(CriarTemplate1Activity.this, "Impossível abrir o recurso", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            try {

                Glide.with(imageView1.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView1.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView1.setImageDrawable(circularBitmapDrawable);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }
            caminho_foto = selectedImagePath;
        }
    }

}
