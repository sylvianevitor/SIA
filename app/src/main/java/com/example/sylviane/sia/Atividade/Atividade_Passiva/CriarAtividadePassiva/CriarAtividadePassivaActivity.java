package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva.CriarAtividadePassivaView;
import com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Activity;
import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.PassivaTemplate1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.PassivaTemplate1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Presenter.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

/**
 * Created by Natasha on 20/06/2018.
 */

public class CriarAtividadePassivaActivity extends AppCompatActivity {

    @BindView(R.id.videoView)VideoView videoView;

    private static final int CODIGO_VIDEO = 1;
    private String caminhoVideo;
    CriarAtividadePassivaView.View criarAtividadePassivaView;
    Context context;
    Atividade atividade;
    AtividadeDAO atividadeDAO = new AtividadeDAO(this);

    @BindView(R.id.btn_video_galeria) Button btnVideoGaleria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_atividade_passiva);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade", -1);
        Log.d("id natasha", Integer.toString(id_atividade));
        atividade = atividadeDAO.getAtividadeId(id_atividade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_template1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                cadastrar(caminhoVideo);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.btn_video_galeria)
    public void selecionaVideo() {
        Intent abreGaleria = new Intent();
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {}

        if (Build.VERSION.SDK_INT <= 19) {
            abreGaleria.setType("video/*");
            abreGaleria.setAction(Intent.ACTION_GET_CONTENT);
            abreGaleria.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            abreGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(abreGaleria, CODIGO_VIDEO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String caminhoVideo = getRealPathFromURI(data.getData());
            Toast.makeText(this,caminhoVideo,Toast.LENGTH_LONG).show();
            videoView.setVideoPath(caminhoVideo);
            videoView.start();
        }
    }

    public void cadastrar(String caminhoVideo) {
        PassivaTemplate1 passivaTemplate1 = new PassivaTemplate1();
        passivaTemplate1.setVideo(caminhoVideo);
        passivaTemplate1.setAtividade(atividade);
        PassivaTemplate1DAO passivaTemplate1DAO = new PassivaTemplate1DAO(this);
        boolean ok= passivaTemplate1DAO.adicionarAquivo(passivaTemplate1);

        Toast toast;
        if (ok == true) {
            if (validar() == true) {
                atividade.setAtiva(Atividade.SITUACAO_ATIVA);
                atividadeDAO.update(atividade);
                toast = Toast.makeText(CriarAtividadePassivaActivity.this, "Atividade cadastrada com sucesso", Toast.LENGTH_LONG);
                toast.show();
                Intent openCadastrarTemaInterativoActivity = new Intent(CriarAtividadePassivaActivity.this, MainActivity.class);
                startActivity(openCadastrarTemaInterativoActivity);
                finish();
            }else{
                atividade.setAtiva(Atividade.SITUACAO_INATIVA);
                atividadeDAO.update(atividade);
            }
        } else{
            toast = Toast.makeText(CriarAtividadePassivaActivity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
            atividade.setAtiva(Atividade.SITUACAO_INATIVA);
            atividadeDAO.update(atividade);
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Video.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    public boolean validar(){

        if (caminhoVideo == null){
            Toast.makeText(CriarAtividadePassivaActivity.this, "Selecionar um vídeo", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
