package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.ProgressDialog.*;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Activity extends AppCompatActivity implements ExecutarTemplate1View{
    ExecutarTemplate1Presenter executarTemplate1Presenter;
    Template1DAO template1DAO;
    AtividadeDAO atividadeDAO;
    //int id_atividade = 0; //id fake
    MediaStore.Audio sound;
    //Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
    @BindView(R.id.imageButton1) ImageButton image1;
    @BindView(R.id.imageButton2) ImageButton image2;
    @BindView(R.id.imageButton3) ImageButton image3;
    @BindView(R.id.btnAudio) Button audio;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_template1);

        //verificar thread
        Log.i("AsyncTask", "Thread: " + Thread.currentThread().getName());
        //thread para acesso ao banco
        i = 0;  // caso 0: thread de buscar imagem
        chamarAsyncTask(i);

        ButterKnife.bind(this);
        executarTemplate1Presenter = new ExecutarTemplate1Presenter(this);
    }
    // chamar criacao de thread
    private void chamarAsyncTask(int i){
        if( i == 0) {
            ImagemBD downloadImage = new ImagemBD();
            //verificar thread
            Log.i("AsyncTask", "AsyncTask sendo chamado Thread: " + Thread.currentThread().getName());
            downloadImage.execute();
        }
        else{
            AudioBD downloadAudio = new AudioBD();
            Log.i("AsyncTask", "AsyncTask sendo chamado Thread: " + Thread.currentThread().getName());
            downloadAudio.execute();
        }
    }

    //thread para buscar imagem no banco
    public class ImagemBD extends AsyncTask<String,Void,Bitmap> {
        ProgressDialog load;
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = show(ExecutarTemplate1Activity.this, "Por favor aguarde ...",
                    "Carregando Atividade ...");
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imagemBitmap = null;
            Log.i("AsyncTask", "Baixando a imagem Thread: " + Thread.currentThread().getName());
           // List<Template1> arquivos = template1DAO.getArquivos(atividade);
            //imagemBitmap = arquivos.getImage();
            return imagemBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap!=null) {
                //image1.setImageBitmap(bitmap); //seta imagem nova
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }

    }

    //thread para buscar audio no banco
    public class AudioBD extends AsyncTask<String,Void,MediaStore.Audio> {
        ProgressDialog load;
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = show(ExecutarTemplate1Activity.this, "Por favor aguarde ...",
                    "Carregando Audio ...");
        }
        @Override
        protected MediaStore.Audio doInBackground(String... params) {
            MediaStore.Audio audio = null;
            Log.i("AsyncTask", "Baixando o audio Thread: " + Thread.currentThread().getName());
            //List<Template1> arquivos = template1DAO.getArquivos(atividade);
            //imagemBitmap = arquivos.getaudio();
            return audio;
        }
        @Override
        protected void onPostExecute(MediaStore.Audio audio){
            if(audio!=null) {
               sound = audio;//seta novo audio
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar o audio " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }

    }


    @OnClick(R.id.btnAudio)
    public void load_info(){executarTemplate1Presenter.load_info();}

    @Override
    public void load_audio(){
        i = 1;
        //verificar thread
        Log.i("AsyncTask", "Thread: " + Thread.currentThread().getName());
        //thread para acesso ao banco
        chamarAsyncTask(i);
        //executar
        MediaPlayer mp = MediaPlayer.create(this,R.raw.sound);
        mp.start();
    }
}

