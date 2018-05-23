package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.model.Template1;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarTemplate1Activity extends AppCompatActivity implements CriarTemplate1View{

    @BindView(R.id.imageButton1)ImageButton imageButton1;
    @BindView(R.id.imageButton2)ImageButton imageButton2;
    @BindView(R.id.imageButton3)ImageButton imageButton3;

    private int PICK_IMAGE_REQUEST = 1;
    private String imagePath1, imagePath2, imagePath3;
    private int id;
    Atividade atividade;

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

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade", -1);

        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
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

                if(id == 1){
                    imageButton1.setImageBitmap(bitmap);
                }else if(id == 2){
                    imageButton2.setImageBitmap(bitmap);
                }else if(id == 3){
                    imageButton3.setImageBitmap(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
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
        template1.setImage(imageButton1.toString());
        template1.setAtividade(atividade);

//        Template1 template2 = new Template1();
//        template2.setImage(imageButton2.toString());
//        template1.setAtividade(atividade);
//
//        Template1 template3 = new Template1();
//        template3.setImage(imageButton3.toString());
//        template1.setAtividade(atividade);

        Log.d("MARI", imageButton1.toString());


        Template1DAO template1DAO = new Template1DAO(CriarTemplate1Activity.this);
        boolean ok1 = template1DAO.adicionarAquivo(template1);
//        boolean ok2 = template1DAO.adicionarAquivo(template2);
//        boolean ok3 = template1DAO.adicionarAquivo(template3);

        Toast toast;
        if (ok1 == true) {
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
