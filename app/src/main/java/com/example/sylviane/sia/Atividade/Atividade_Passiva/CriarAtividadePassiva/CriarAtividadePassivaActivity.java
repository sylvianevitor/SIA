package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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

/**
 * Created by Natasha on 20/06/2018.
 */

public class CriarAtividadePassivaActivity extends AppCompatActivity {

    private static final int CODIGO_VIDEO = 1;
    private String caminhoVideo;
    CriarAtividadePassivaView.View criarAtividadePassivaView;
    Context context;
    Atividade atividade;

    @BindView(R.id.btn_video_galeria) Button btnVideoGaleria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_atividade_passiva);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade", -1);
        Log.d("id natasha", Integer.toString(id_atividade));

        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        atividade = atividadeDAO.getAtividadeId(id_atividade);
    }

    @OnClick(R.id.btn_video_galeria)
    public void selecionaVideo() {
        Intent abreGaleria = new Intent();

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
            cadastrar(caminhoVideo);
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
            toast = Toast.makeText(CriarAtividadePassivaActivity.this, "Atividade cadastrada com sucesso", Toast.LENGTH_LONG);
            toast.show();
            Intent openCadastrarTemaInterativoActivity = new Intent(CriarAtividadePassivaActivity.this, MainActivity.class);
            startActivity(openCadastrarTemaInterativoActivity);

        } else{
            toast = Toast.makeText(CriarAtividadePassivaActivity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG);
            toast.show();
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
}
