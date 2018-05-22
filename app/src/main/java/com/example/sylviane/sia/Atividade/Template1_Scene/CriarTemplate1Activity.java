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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene.DescricaoAtividadePresenter;
import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Tema_Scene.CadastrarTemas.CadastrarTemasInterativosActivity;
import com.example.sylviane.sia.Tema_Scene.Tema_Interativo.TemaInterativoActivity;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.model.Template1;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarTemplate1Activity extends AppCompatActivity implements CriarTemplate1View{

    @BindView(R.id.imageButton1)ImageButton imageButton1;
    @BindView(R.id.imageButton2)ImageButton imageButton2;
    @BindView(R.id.imageButton3)ImageButton imageButton3;

    private static final int REQUEST_CAMERA = 123;
    private int id;

    CriarTemplate1Presenter criarTemplate1Presenter;

    String selectedImagePath;
    String caminho_foto1, caminho_foto2, caminho_foto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_template1);

        ButterKnife.bind(this);
        selectedImagePath = new String();

        criarTemplate1Presenter = new CriarTemplate1Presenter(this);
    }

    @OnClick(R.id.imageButton1)
    public void ligarCamera1(){
        id = 1;
        criarTemplate1Presenter.ligarCamera(id);
    }

    @OnClick(R.id.imageButton2)
    public void ligarCamera2(){
        id =2;
        criarTemplate1Presenter.ligarCamera(id);
    }

    @OnClick(R.id.imageButton3)
    public void ligarCamera3(){
        id = 3;
        criarTemplate1Presenter.ligarCamera(id);
    }

    @Override
    public void camera(int id){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamera.resolveActivity(getPackageManager()) != null) {
            selectedImagePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
            File arquivoFoto = new File(selectedImagePath);
            Uri fileUri = FileProvider.getUriForFile(this, "com.example.sylviane.sia.fileprovider", arquivoFoto);
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

                if(id == 1){
                    Glide.with(imageButton1.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageButton1) {

                    });

                    caminho_foto1 = selectedImagePath;

                } else if(id == 2){
                    Glide.with(imageButton2.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageButton2) {

                    });

                    caminho_foto2 = selectedImagePath;

                } else if(id == 3){
                    Glide.with(imageButton3.getContext()).load(selectedImagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageButton3) {
//                        @Override
//                        protected void setResource(Bitmap resource) {
//                            RoundedBitmapDrawable circularBitmapDrawable =
//                                    RoundedBitmapDrawableFactory.create(imageButton3.getContext().getResources(), resource);
//                            circularBitmapDrawable.setCircular(true);
//                            imageButton3.setImageDrawable(circularBitmapDrawable);
//                        }
                    });

                    caminho_foto3 = selectedImagePath;
                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_template1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                criarTemplate1Presenter.cadastrarTemplate1();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void cadastrar(){

        Template1 template1 = new Template1();
        template1.setImage(caminho_foto1);

        Template1 template2 = new Template1();
        template2.setImage(caminho_foto2);

        Template1 template3 = new Template1();
        template3.setImage(caminho_foto3);


        Template1DAO template1DAO = new Template1DAO(CriarTemplate1Activity.this);
        boolean ok1 = template1DAO.adicionarAquivo(template1);
        boolean ok2 = template1DAO.adicionarAquivo(template2);
        boolean ok3 = template1DAO.adicionarAquivo(template3);

        Toast toast;
        if (ok1 == true && ok2 == true && ok3 == true) {
            toast = Toast.makeText(CriarTemplate1Activity.this, "Atividade cadastrada com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent openCadastrarTemaInterativoActivity = new Intent(CriarTemplate1Activity.this, MainActivity.class);
            startActivity(openCadastrarTemaInterativoActivity);

        } else{
            toast = Toast.makeText(CriarTemplate1Activity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
        }


    }

}
