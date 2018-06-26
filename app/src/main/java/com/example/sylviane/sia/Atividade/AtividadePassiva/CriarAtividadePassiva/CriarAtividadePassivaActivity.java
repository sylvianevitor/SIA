package com.example.sylviane.sia.Atividade.AtividadePassiva.CriarAtividadePassiva;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Natasha on 20/06/2018.
 */

public class CriarAtividadePassivaActivity extends AppCompatActivity implements CriarAtividadePassivaView.View{

    private static final int CODIGO_VIDEO = 1;
    private String caminhoVideo;
    CriarAtividadePassivaView.View criarAtividadePassivaView;
    Context context;

    @BindView(R.id.btn_video_galeria) Button btnVideoGaleria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_atividade_passiva);
        ButterKnife.bind(this);
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
        }
    }

    @Override
    public void abreActivity(Intent intent, Integer codigo) {
    }

    @Override
    public void carregaVideo(String caminhoArquivo) {
    }

    @Override
    public void cadastrar() {

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
